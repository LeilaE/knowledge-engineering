% Additional examination suggestions
% Name of examination and a list of symptoms it is suggested for
% additional_test(symptoms(person_name, symptom_list), test_name) -> test_name if symptom_list contained in disease
% confirmed_diagnosis(symptoms(person_name, list_of_symptoms), X) X -> confirmed_disease_name based on tests
% test_name(person_name, test_parameter).

%low
additional_test(symptoms(X, S), hemoglobin_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

%low
additional_test(symptoms(X, S), iron_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

%low
additional_test(symptoms(X, S), b12_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

%low
additional_test(symptoms(X, S), folic_acid_check) :-
    disease(anemia, S2), contains(S2, S),  person(X).

%high
additional_test(symptoms(X, S), blood_sugar_level) :-
    disease(diabetes, S2), contains(S2, S), person(X).

%high
additional_test(symptoms(X, S), glucose_tolerance) :-
    disease(diabetes, S2), contains(S2, S), person(X).

%high
additional_test(symptoms(X, S), glycated_hemoglobin) :-
    disease(diabetes, S2), contains(S2, S), person(X).

%low
additional_test(symptoms(X, S), d_dimer_level) :-
    disease(blood_clot, S2), contains(S2, S), person(X).

%low
additional_test(symptoms(X, S), fibrin_degradation_fragment) :-
    disease(blood_clot, S2), contains(S2, S), person(X).

%high
additional_test(symptoms(X, S), blood_pressure) :-
    disease(hypertension, S2), contains(S2, S), person(X).

%high
additional_test(symptoms(X, S), doppler_ultrasound_blood_flow) :-
    disease(hypertension, S2), contains(S2, S), person(X).

%low
additional_test(symptoms(X, S), blood_pressure) :-
    disease(hypotension, S2), contains(S2, S), person(X).

%low
additional_test(symptoms(X, S), electrocardiogram_heart_rate) :-
    disease(hypotension, S2), contains(S2, S), person(X).

%ow
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

% high
additional_test(symptoms(X, S), lymphoma_cells_level) :-
    disease(lymphoma, S2), contains(S2, S), person(X).


% high
additional_test(symptoms(X, S), pcr_chromosome_changes) :-
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

% high
additional_test(symptoms(X, S), uric_acid_level) :-
    disease(thrombocytosis, S2), contains(S2, S), person(X).

% high
additional_test(symptoms(X, S), bilirubin_level) :-
    disease(thrombocytosis, S2), contains(S2, S), person(X).

% low
additional_test(symptoms(X, S), erythropoietin_level) :-
    disease(thrombocytosis, S2), contains(S2, S), person(X).

% high
additional_test(symptoms(X, S), red_cell_count) :-
    disease(thrombocytosis, S2), contains(S2, S), person(X).

% high
additional_test(symptoms(X, S), hemoglobin_check) :-
    disease(thrombocytosis, S2), contains(S2, S), person(X).

% high
additional_test(symptoms(X, S), hematocrit_level) :-
    disease(thrombocytosis, S2), contains(S2, S), person(X).

% low
additional_test(symptoms(X, S), iron_check) :-
    disease(thrombocytosis, S2), contains(S2, S), person(X).

% low
additional_test(symptoms(X, S), b12_check) :-
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

confirmed_diagnosis(symptoms(X, S), thrombus_blood_clot) :-
    (disease(blood_clot, S2), contains(S2, S), person(X)),
    (d_dimer_level(X,P1), P1  = low;
    genetics(X, Y), member(blood_clot, Y)).

confirmed_diagnosis(symptoms(X, S), embolus_blood_clot) :-
    (disease(blood_clot, S2), contains(S2, S), person(X)),
    (fibrin_degradation_fragment(X,P1), P1  = low;
    genetics(X, Y), member(blood_clot, Y)).

confirmed_diagnosis(symptoms(X, S), isolated_systolic_hypertension) :-
    (disease(hypertension, S2), contains(S2, S), person(X)),
    (age(X,P2), P2 > 55,
    blood_pressure(X,P1), P1  = high;
    genetics(X, Y), member(hypertension, Y)).

confirmed_diagnosis(symptoms(X, S), resistant_hypertension_hypertension) :-
    (disease(hypertension, S2), contains(S2, S),  person(X)),
    (doppler_ultrasound_blood_flow(X, P13), P13 = high;
    blood_pressure(X, P22), P22 = high).

confirmed_diagnosis(symptoms(X, S), neurally_mediated_hypotension) :-
    (disease(hypotension, S2), contains(S2, S),  person(X)),
    (age(X,P2), P2 < 20,
    echodiagram_heart_rytam(X, P13), P13 = low;
    blood_pressure(X, P22), P22 = low).

confirmed_diagnosis(symptoms(X, S), postprandial_hypotension) :-
    (disease(hypotension, S2), contains(S2, S),  person(X)),
    (age(X,P2), P2 > 35,
    electrocardiogram_heart_rate(X, P13), P13 = low;
    blood_pressure(X, P22), P22 = low).

confirmed_diagnosis(symptoms(X, S), lymphocytic_leukemia) :-
    (disease(leukemia, S2), contains(S2, S),  person(X)),
    (white_cell_count(X, P13), P13 = high;
    platelets_count(X, P14), P14 = low;
    red_cell_count(X, P22), P22 = low).

confirmed_diagnosis(symptoms(X, S), myeloid_leukemia ) :-
    (disease(leukemia, S2), contains(S2, S),  person(X)),
    (platelets_count(X, P13), P13 = low;
    genetics(X, Y), member(leukemia, Y)).


confirmed_diagnosis(symptoms(X, S), hodgkin_lymphoma ) :-
    (disease(lymphoma, S2), contains(S2, S),  person(X)),
    ((age(X,P2), P2 < 34;
    age(X,P3), P3 > 60),
    lymphoma_cells_level(X, P13), P13 = high;
    genetics(X, Y), member(lymphoma, Y)).

confirmed_diagnosis(symptoms(X, S), non_hodgkin_lymphoma ) :-
    (disease(lymphoma, S2), contains(S2, S),  person(X)),
    (pcr_chromosome_changes(X, P13), P13 = high;
    genetics(X, Y), member(lymphoma, Y)).

confirmed_diagnosis(symptoms(X, S), hemophilia_type_A ) :-
    (disease(hemophilia, S2), contains(S2, S),  person(X)),
    ((red_cell_count(X, P13), P13 = high,
     white_cell_count(X, P14), P14 = normal,
     platelets_count(X, P15), P15 = normal),
     genetics(X, Y), member(hemophilia, Y)).

confirmed_diagnosis(symptoms(X, S), hemophilia_type_B ) :-
    (disease(hemophilia, S2), contains(S2, S),  person(X)),
    (red_cell_count(X, P13), P13 = high;
     genetics(X, Y), member(hemophilia, Y)).

confirmed_diagnosis(symptoms(X, S), hemophilia_type_C ) :-
    (disease(hemophilia, S2), contains(S2, S),  person(X)),
    ((aptt_clothing_factor(X, P13), P13 = low,
    active(X,P3), P3 = no);
    genetics(X, Y), member(hemophilia, Y)).

confirmed_diagnosis(symptoms(X, S), polycythaemia_vera) :-
    (disease(thrombocytosis, S2), contains(S2, S),  person(X)),
    (age(X,P2), P2 > 55,
    ((red_cell_count(X, P13), P13 = high, smoker(X, P17), P17 = yes),
    hemoglobin_check(X, P55), P55 = high,
    hematocrit_level(X, P44), P44 = high);
    erythropoietin_level(X, P66), P66 = low).

confirmed_diagnosis(symptoms(X, S), primary_myelofibrosis) :-
    (disease(thrombocytosis, S2), contains(S2, S),  person(X)),
    ((age(X,P2), P2 > 40; active(X, P17), P17 = no),
    ((uric_acid_level(X, P13), P13 = high, genetics(X, Y), member(thrombocytosis, Y)));
    bilirubin_level(X, P55), P55 = high;
    red_cell_count(X, P55), P55 = high).

confirmed_diagnosis(symptoms(X, S), myelodysplastic_syndromes) :-
    (disease(thrombocytosis, S2), contains(S2, S),  person(X)),
    (active(X, P17), P17 = no,
    ((iron_check(X, P13), P13 = low, genetics(X, Y), member(thrombocytosis, Y)));
    b12_check(X, P55), P55 = high).

confirmed_diagnosis(symptoms(X, S), light_chain_myeloma) :-
    (disease(myeloma, S2), contains(S2, S),  person(X)),
    (pregnant(X, P17), P17 = yes),
    (calcium_level(X, P13), P13 = high,
    ldh_level(X, P55), P55 = low).

confirmed_diagnosis(symptoms(X, S), solitary_plasmacytoma) :-
    (disease(myeloma, S2), contains(S2, S),  person(X)),
    (genetics(X, Y), member(myeloma, Y);
    (creatinine_level(X, P13), P13 = high;
    albumin_level(X, P55), P55 = low)).




