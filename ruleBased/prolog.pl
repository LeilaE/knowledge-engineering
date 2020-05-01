% symptoms
symptom(fatigue).
symptom(weakness).
symptom(pale_skin).
symptom(breathing_difficulties).
symptom(bleeding).
symptom(swelling).
symptom(change_in_color).
symptom(cramps).
symptom(bruising).
symptom(increased_thirst).
symptom(headache).
symptom(troube_concentrating).
symptom(blurred_vision).
symptom(frequent_peeing).
symptom(weight_loss).
symptom(irregular_heartbeat).
symptom(pounding_in_the_neck).
symptom(pounding_in_the_ears).
symptom(vision_problems).
symptom(headache).
symptom(chest_pain).

% people
person(stasa).
person(milica).
person(ana).
person(masa).
person(aleksandar).
person(petar).
person(mihajlo).
person(nikola).
person(srdjan).

% additional test for anemia that confirmes pernicious anemia if
% deficient(normal)
b12_check(stasa, deficient).
folic_acid_check(stasa, normal).
 number_of_neutrophils(stasa, deficent).

% additional test for anemia that confirmes iron-deficinecy anemia if
% decreased(normal)
hemoglobin_check(stasa, normal).
iron_check(stasa, normal).


% additional test for diabetes that confirmes iron-deficinecy anemia if
% decreased(normal)
blood_sugar_level(mihajlo, high).


%utice na anemiju (65+), blood cloth (60+)
age(stasa, 22).
age(milica, 23).
age(ana, 35).
age(masa, 51).
age(aleksandar, 40).
age(petar, 32).
age(mihajlo, 55 ).
age(nikola, 76).
age(srdjan, 66).

%utice na dijabetes, high_blood_pressure
activity(stasa, inactive).
activity(milica, active).
activity(ana, active).
activity(masa, active).
activity(aleksandar, inactive).
activity(petar, inactive).
activity(mihajlo, active).
activity(nikola, inactive).
activity(srdjan, active).

%utice na blood cloth
smoker(stasa, no).
smoker(milica, yes).
smoker(ana, yes).
smoker(masa, no).
smoker(aleksandar, no).
smoker(petar, yes).
smoker(mihajlo, yes).
smoker(nikola, yes).
smoker(srdjan, no).


genetics(stasa, [ high_blood_pressure]).
genetics(milica, []).
genetics(ana, [diabetes]).
genetics(masa, [anemia]).
genetics(aleksandar, []).
genetics(petar, []).
genetics(mihajlo, [high_blood_pressure]).
genetics(nikola, []).
genetics(srdjan, []).

pregnant(ana).
pregnant(masa).

% END_OF_PEOPLE_FILE

% Symptom and disease knowledge base
% Name of the disease and a list of associated symptoms
% disease(name, [sym1, sym2, sym3...])
% suggest_diagnosis(X, symptoms(person_name, [sym1, sym2, sym3...])) -> X = suggested_disease

symptoms(person, list_of_symptoms).

disease(anemia,[fatigue, weakness, pale_skin, breathing_difficulties]).
disease(blood_cloth,[bleeding, swelling, change_in_color, cramps, bruising]).
disease(diabetes,[fatigue, increased_thirst, headache, troube_concentrating, blurred_vision, frequent_peeing, weight_loss]).
disease(high_blood_pressure, [irregular_heartbeat, pounding_in_the_neck, pounding_in_the_ears, vision_problems, headache, chest_pain, breathing_difficulties]).


contains(S,[]).
contains(S,[H|T]) :- member(H,S), contains(S,T).

suggest_diagnosis( symptoms(X, S), B) :- disease(B,S2), contains(S2,S), person(X).

% END_OF_SYMPTOMS_FILE

% Additional examination suggestions
% Name of examination and a list of symptoms it is suggested for
% additional_test(symptoms(person_name, symptom_list), test_name) -> test_name if symptom_list contained in disease
% confirmed_diagnosis(symptoms(person_name, list_of_symptoms), X) X -> confirmed_disease_name based on tests
% test_name(person_name, test_parameter).

% contains(S,[]).
% contains(S,[H|T]) :- member(H,S), contains(S,T).

additional_test(symptoms(X, S), hemoglobin_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

additional_test(symptoms(X, S), iron_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

additional_test(symptoms(X, S), b12_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

additional_test(symptoms(X, S), folic_acid_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

additional_test(symptoms(X, S), number_of_neutrophils) :-
    disease(anemia, S2), contains(S2, S),  person(X).

additional_test(symptoms(X, S), blood_sugar_level) :-
    disease(diabetes, S2), contains(S2, S), person(X) .

confirmed_diagnosis(symptoms(X, S), pernicious_anemia) :-
    disease(anemia, S2), contains(S2, S), person(X),
    b12_check(X, P1), P1 = deficient;
    folic_acid_check(X, P2), P2 = deficient;
    number_of_neutrophils(X, P3), P3 = deficient.

confirmed_diagnosis(symptoms(X, S), iron-deficiency_anemia) :-
    disease(anemia, S2), contains(S2, S), person(X),
    hemoglobin_check(X, P1), P1 = deficient;
    iron_check(X, P2), P2 = deficient.

confirmed_diagnosis(symptoms(X, S),  diabetes_type_1) :-
    disease(diabetes, S2), contains(S2, S), person(X),
    age < 18,
    blood_sugar_level(X,P1), P1  = high;
    genetics(X, Y), member(diabetes, Y).


confirmed_diagnosis(symptoms(X, S),  diabetes_type_2) :-
    disease(diabetes, S2), contains(S2, S), person(X),
    age > 18,
    blood_sugar_level(X,P1), P1  = high;
    genetics(X, Y), member(diabetes, Y).


confirmed_diagnosis(symptoms(X, S), gestational_diabetes) :-
    disease(diabetes, S2), contains(S2, S), person(X),
    pregnant(X),
    blood_sugar_level(X,P1), P1  = high;
    genetics(X, Y), member(diabetes, Y).

%END_OF_ADDITIONAL_EXAMINATION_FILE

% Treatment suggestions
% treatment_for([confirmed_disease_name], X) -> X = [tre1, tre2,...]

treatment(iron_pills, [iron-deficiency_anemia]).
treatment(diet, [iron-deficiency_anemia, diabetes_type_1, diabetes_type_2, gestational_diabetes]).
treatment(blood_transfusion, [iron-deficiency_anemia, pernicious_anemia]).
treatment(vitamin_b12_injections, [pernicious_anemia]).
treatment(insulin_injections, [diabetes_type_1, diabetes_type_2]).
treatment(blood_sugar_monitoring, [diabetes_type_1, diabetes_type_2, gestational_diabetes]).
treatment(excercise, [diabetes_type_1, diabetes_type_2, gestational_diabetes] ).

% contains(S,[]).
% contains(S,[H|T]) :- member(H,S), contains(S,T).

treatment_for(X, T) :- treatment(T, L), contains(L, X).
