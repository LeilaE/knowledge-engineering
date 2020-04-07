% Additional examination suggestions
% Name of examination and a list of symptoms it is suggested for
% additional_test(symptoms(person_name, symptom_list), test_name) -> test_name if symptom_list contained in disease
% confirmed_diagnosis(person_name, X) X -> confirmed_disease_name based on tests
% test_name(person_name, test_parameter).

import('symptoms.pl').

contains(S,[]).
contains(S,[H|T]) :- member(H,S), contains(S,T).

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
Gestational diabetes
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

