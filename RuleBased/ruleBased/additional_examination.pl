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
additional_test(symptoms(X, S), albumin_level) :-
    disease(myeloma, S2), contains(S2, S), person(X).

% high
additional_test(symptoms(X, S), ldh_level) :-
    disease(myeloma, S2), contains(S2, S), person(X) .


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
