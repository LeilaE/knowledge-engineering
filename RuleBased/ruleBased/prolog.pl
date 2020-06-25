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
genetics(petar, blood_clot).
genetics(srdjan, hypertension).
genetics(nikola, leukemia).
genetics(nikola, hemophilia).
genetics(milica, thrombocytosis).

pregnant(ana).
pregnant(masa).
% Symptom and disease knowledge base
% Name of the disease and a list of associated symptoms
% disease(name, [sym1, sym2, sym3...])
% suggest_diagnosis(X, symptoms(person_name, [sym1, sym2, sym3...])) -> X = suggested_disease

symptoms(person, list_of_symptoms).

disease(anemia,[fatigue, weakness, pale_skin, breathing_difficulties]).
disease(diabetes,[fatigue, weakness, increased_thirst, headache, trouble_concentrating, vision_problems, frequent_peeing, weight_loss]).
disease(blood_clot,[excessive_bleeding, swelling, change_in_color, cramps, bruising]).
disease(hypertension, [irregular_heartbeat, pounding_in_the_neck, pounding_in_the_ears, vision_problems, headache, chest_pain, breathing_difficulties]).
disease(hypotension, [dizziness, lightheadedness, fatigue, weakness, vision_problems, nausea, trouble_concentrating]).
disease(leukemia, [chills, fever, fatigue, weakness, swollen_lymph_nodes, swelling, enlarged_liver, weight_loss, nosebleeds, bone_pain, sweating, pale_skin]).
disease(lymphoma, [chills, fever, fatigue, weakness, sweating, breathing_difficulties, itchy_skin, weight_loss]).
disease(hemophilia, [bruising, excessive_bleeding, bloody_urine, bloody_stool, nosebleeds, unexplained_irritability]).
disease(thrombocytosis, [headache, dizziness, lightheadedness, weakness, numb_limbs]).
disease(myeloma, [spine_pain, bone_pain, chest_pain, nausea, appetite_loss, fatigue, weakness, mental_confusion, weight_loss, numb_limbs, increased_thirst]).



contains(S,[]).
contains(S,[H|T]) :- member(H,S), contains(S,T).

suggest_diagnosis( symptoms(X, S), B) :- disease(B,S2), contains(S2,S), person(X).

% symptoms
symptom(fatigue).
symptom(weakness).
symptom(pale_skin).
symptom(breathing_difficulties).
symptom(swelling).
symptom(change_in_color).
symptom(cramps).
symptom(bruising).
symptom(increased_thirst).
symptom(headache).
symptom(troube_concentrating).
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
symptom(chills).
symptom(fever).
symptom(swollen_lymph_nodes).
symptom(enlarged_liver).
symptom(nosebleeds).
symptom(bone_pain).
symptom(sweating).
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
genetic(diabetes).
genetic(blood_clot).
genetic(hypertension).
genetic(hypotension).
genetic(leukemia).
genetic(lymphoma).
genetic(hemophilia).
genetic(thrombocytosis).
genetic(myeloma).

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




% Treatment suggestions
% treatment_for([confirmed_disease_name], X) -> X = [tre1, tre2,...]

treatment(iron_pills, [iron_deficiency_anemia]).
treatment(diet, [iron_deficiency_anemia, diabetes_type_1, diabetes_type_2, gestational_diabetes, neurally_mediated_hypotension, postprandial_hypotension]).
treatment(blood_transfusion, [iron_deficiency_anemia, pernicious_anemia]).
treatment(vitamin_b12_injections, [pernicious_anemia]).
treatment(insulin_injections, [diabetes_type_1, diabetes_type_2]).
treatment(blood_sugar_monitoring, [diabetes_type_1, diabetes_type_2, gestational_diabetes]).
treatment(excercise, [diabetes_type_1, diabetes_type_2, gestational_diabetes, isolated_systolic_hypertension, resistant_hypertension_hypertension] ).
treatment(blood_thiners, [trombus_blood_clot, embolus_blood_clot]).
treatment(surgery, [trombus_blood_clot, embolus_blood_clot, lymphocytic_leukemia, myeloid_leukemia, hodgkin_lymphoma, solitary_plasmacytoma]).
treatment(blood_pressure_pills, [isolated_systolic_hypertension, resistant_hypertension_hypertension, neurally_mediated_hypotension]).
treatment(chemotherapy, [lymphocytic_leukemia, myeloid_leukemia, hodgkin_lymphoma, non_hodgkin_lymphoma, light_chain_myeloma, solitary_plasmacytoma]).
treatment(radiation, [lymphocytic_leukemia, myeloid_leukemia, hodgkin_lymphoma, non_hodgkin_lymphoma, polycythaemia_vera, solitary_plasmacytoma]).
treatment(stem_cell_transplant, [myeloid_leukemia, non_hodgkin_lymphoma, primary_myelofibrosis, myelodysplastic_syndromes]).
treatment(desmopressin, [hemophilia_type_A, hemophilia_type_C] ).
treatment(antifibrinolytics, [hemophilia_type_A]).
treatment(replacement_therapy, [hemophilia_type_B]).
treatment(hormone_therapy, [hemophilia_type_C]).
treatment(phlebotomy, [polycythaemia_vera]).
treatment(hydroxyurea, [polycythaemia_vera] ).
treatment(jakafi, [primary_myelofibrosis]).
treatment(bone_marrow_transplant, [light_chain_myeloma]).


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
hemoglobin_check(aleksandar,high).
iron_check(aleksandar,low).
b12_check(aleksandar,normal).
folic_acid_check(aleksandar,low).
white_cell_count(aleksandar,low).
red_cell_count(aleksandar,high).
platelets_count(aleksandar,low).
aptt_clothing_factor(aleksandar,low).
d_dimer_level(petar,high).
fibrin_degradation_fragment(petar,low).
blood_pressure(ana,low).
electrocardiogram_heart_rate(ana,low).
echodiagram_heart_rytam(ana,low).
lymphoma_cells_level(ana,high).
pcr_chromosome_changes(ana,high).
creatinine_level(ana,low).
calcium_level(ana,low).
albumin_level(ana,low).
ldh_level(ana,low).
