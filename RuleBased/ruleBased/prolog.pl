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

% additional test for anemia that confirmes iron-deficinecy anemia if

% additional test for diabetes that confirmes iron-deficinecy anemia if

% utice na anemiju (65+), blood cloth (60+)
age(stasa, 22).
age(milica, 23).
age(ana, 35).
age(masa, 51).
age(aleksandar, 40).
age(petar, 32).
age(mihajlo, 55 ).
age(nikola, 76).
age(srdjan, 66).

% utice na dijabetes, high_blood_pressure
activity(stasa, inactive).
activity(milica, active).
activity(ana, active).
activity(masa, active).
activity(aleksandar, inactive).
activity(petar, inactive).
activity(mihajlo, active).
activity(nikola, inactive).
activity(srdjan, active).

% utice na blood cloth
smoker(stasa, no).
smoker(milica, yes).
smoker(ana, yes).
smoker(masa, no).
smoker(aleksandar, no).
smoker(petar, yes).
smoker(mihajlo, yes).
smoker(nikola, yes).
smoker(srdjan, no).

genetics(stasa, high_blood_pressure).
genetics(stasa, diabetes).
genetics(ana, diabetes).
genetics(masa, anemia).
genetics(mihajlo, high_blood_pressure).

pregnant(ana).
pregnant(masa).
% Symptom and disease knowledge base
% Name of the disease and a list of associated symptoms
% disease(name, [sym1, sym2, sym3...])
% suggest_diagnosis(X, symptoms(person_name, [sym1, sym2, sym3...])) -> X = suggested_disease

symptoms(person, list_of_symptoms).

disease(anemia,[fatigue, weakness, pale_skin, breathing_difficulties]).
disease(blood_clot,[bleeding, swelling, change_in_color, cramps, bruising]).
disease(diabetes,[fatigue, increased_thirst, headache, troube_concentrating, blurred_vision, frequent_peeing, weight_loss]).
disease(hypertension, [irregular_heartbeat, pounding_in_the_neck, pounding_in_the_ears, vision_problems, headache, chest_pain, breathing_difficulties]).
disease(hypotension, [dizziness, lightheadedness, fatigue, vision_problems, nausea, lack_of_concentration]).
disease(leukemia, [chills, fever, fatigue, weakness, swollen_lymph_nodes, enlarged_liver, weight_loss, nosebleeds, bone_pain, sweating]).
disease(lymphoma, [fever, fatigue, sweating, shortness_of_breath, itchy_skin, weight_loss]).
disease(hemophilia, [bruising, excessive_bleeding, bloody_urine, bloody_stool, nosebleeds, unexplained_irritability]).
disease(thrombocytosis, [headache, dizziness, lightheadedness, weakness, numb_limbs]).
disease(myeloma, [spine_pain, bone_pain, chest_pain, nausea, appetite_loss, fatigue, mental_confusion, weight_loss, numb_limbs, increased_thirst]).


contains(S,[]).
contains(S,[H|T]) :- member(H,S), contains(S,T).

suggest_diagnosis( symptoms(X, S), B) :- disease(B,S2), contains(S2,S), person(X).

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
symptom(dizziness).
symptom(lightheadedness).
symptom(vision_problems).
symptom(nausea).
symptom(lack_of_concentration).
symptom(chills).
symptom(fever).
symptom(swollen_lymph_nodes).
symptom(enlarged_liver).
symptom(nosebleeds).
symptom(bone_pain).
symptom(sweating).
symptom(shortness_of_breath).
symptom(itchy_skin).
symptom(excessive_bleeding).
symptom(bloody_urine).
symptom(bloody_stool).
symptom(unexplained_irritability).
symptom(numb_limbs).
symptom(spine_pain).
symptom(appetite_loss).
symptom(mental_confusion).

% genetics
genetic(anemia).
genetic(blood_cloth).
genetic(diabetes).
genetic(hypertension).
genetic(hypotension).
genetic(leukemia).
genetic(lymphoma).
genetic(hemophilia).
genetic(thrombocytosis).
genetic(myeloma).


% genetics
genetic(high_blood_pressure).
genetic(diabetes).
genetic(anemia).
% Additional examination suggestions
% Name of examination and a list of symptoms it is suggested for
% additional_test(symptoms(person_name, symptom_list), test_name) -> test_name if symptom_list contained in disease
% confirmed_diagnosis(symptoms(person_name, list_of_symptoms), X) X -> confirmed_disease_name based on tests
% test_name(person_name, test_parameter).

additional_test(symptoms(X, S), hemoglobin_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

additional_test(symptoms(X, S), iron_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

additional_test(symptoms(X, S), b12_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

additional_test(symptoms(X, S), folic_acid_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

additional_test(symptoms(X, S), blood_sugar_level) :-
    disease(diabetes, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), d_dimer_level) :-
    disease(blood_clot, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), fibrin_degradation_fragment) :-
    disease(blood_clot, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), blood_pressure) :-
    disease(hypertension, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), doppler_ultrasound_blood_flow) :-
    disease(hypertension, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), blood_pressure) :-
    disease(hypotension, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), electrocardiogram_heart_rate) :-
    disease(hypotension, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), echodiagram_heart_rytam) :-
    disease(hypotension, S2), contains(S2, S), person(X).

% high
additional_test(symptoms(X, S), white_cell_count) :-
    disease(leukemia, S2), contains(S2, S), person(X).

% low
additional_test(symptoms(X, S), red_cell_count) :-
    disease(leukemia, S2), contains(S2, S), person(X).

% low
additional_test(symptoms(X, S), platelets_count) :-
    disease(leukemia, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), lymphoma_cells_level) :-
    disease(lymphoma, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), PCR_chromosome_changes) :-
    disease(lymphoma, S2), contains(S2, S), person(X).

% high
additional_test(symptoms(X, S), red_cell_count) :-
    disease(hemophilia, S2), contains(S2, S), person(X).

% normal
additional_test(symptoms(X, S), white_cell_count) :-
    disease(hemophilia, S2), contains(S2, S), person(X).

% normal
additional_test(symptoms(X, S), platelets_count) :-
    disease(hemophilia, S2), contains(S2, S), person(X).

% low
additional_test(symptoms(X, S), aptt_clothing_factor) :-
    disease(hemophilia, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), iron_check) :-
    disease(thrombocytosis, S2), contains(S2, S), person(X).

additional_test(symptoms(X, S), jak2_gene_presence) :-
    disease(thrombocytosis, S2), contains(S2, S), person(X).

% high
additional_test(symptoms(X, S), creatinine_level) :-
    disease(myeloma, S2), contains(S2, S), person(X).

% low
additional_test(symptoms(X, S), calcium_level) :-
    disease(myeloma, S2), contains(S2, S), person(X).

 % low
additional_test(symptoms(X, S), ldh_level) :-
    disease(myeloma, S2), contains(S2, S), person(X).

 % low
additional_test(symptoms(X, S), albumin_level) :-
    disease(myeloma, S2), contains(S2, S), person(X).

confirmed_diagnosis(symptoms(X, S), pernicious_anemia) :-
    (disease(anemia, S2), contains(S2, S),  person(X)),
    (b12_check(X, P1), P1 = low;
    folic_acid_check(X, P2), P2 = low).

confirmed_diagnosis(symptoms(X, S), iron_deficiency_anemia) :-
    (disease(anemia, S2), contains(S2, S),  person(X)),
    (hemoglobin_check(X, P13), P13 = low;
    iron_check(X, P22), P22 = low).

confirmed_diagnosis(symptoms(X, S),  diabetes_type_1) :-
    (disease(diabetes, S2), contains(S2, S), person(X)),
    (age(X,P2), P2 < 18,
    blood_sugar_level(X,P1), P1  = high;
    genetics(X, Y), member(diabetes, Y)).


confirmed_diagnosis(symptoms(X, S),  diabetes_type_2) :-
    (disease(diabetes, S2), contains(S2, S), person(X)),
    (blood_sugar_level(X,P1), P1  = high;
    genetics(X, Y), member(diabetes, Y)).


confirmed_diagnosis(symptoms(X, S), gestational_diabetes) :-
    (disease(diabetes, S2), contains(S2, S), person(X)),
    (pregnant(X),
    blood_sugar_level(X,P1), P1  = high;
    genetics(X, Y), member(diabetes, Y)).
% Treatment suggestions
% treatment_for([confirmed_disease_name], X) -> X = [tre1, tre2,...]

treatment(iron_pills, [iron_deficiency_anemia]).
treatment(diet, [iron_deficiency_anemia, diabetes_type_1, diabetes_type_2, gestational_diabetes]).
treatment(blood_transfusion, [iron_deficiency_anemia, pernicious_anemia]).
treatment(vitamin_b12_injections, [pernicious_anemia]).
treatment(insulin_injections, [diabetes_type_1, diabetes_type_2]).
treatment(blood_sugar_monitoring, [diabetes_type_1, diabetes_type_2, gestational_diabetes]).
treatment(excercise, [diabetes_type_1, diabetes_type_2, gestational_diabetes] ).

containsTreatment(S,[H|T]) :- member(H,S); containsTreatment(S,T).

treatment_for(X, T) :- treatment(T, L), containsTreatment(L, X).

blood_sugar_level(ana,high).
hemoglobin_check(ana,low).
iron_check(ana,low).
b12_check(ana,low).
folic_acid_check(ana,low).
hemoglobin_check(stasa,low).
iron_check(stasa,normal).
b12_check(stasa,high).
folic_acid_check(stasa,normal).
blood_sugar_level(stasa,normal).
person(pera).
age(pera,27).
activity(pera,inactive).
smoker(pera,yes).
pregnant(pera,no).
genetics(pera,high_blood_pressure).
genetics(pera,diabetes).
hemoglobin_check(pera,normal).
iron_check(pera,normal).
b12_check(pera,low).
folic_acid_check(pera,high).
person(anica).
age(anica,62).
activity(anica,inactive).
smoker(anica,yes).
pregnant(anica,no).
genetics(anica,diabetes).
genetics(anica,anemia).
blood_sugar_level(pera,normal).
