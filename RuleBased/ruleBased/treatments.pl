% Treatment suggestions
% treatment_for([confirmed_disease_name], X) -> X = [tre1, tre2,...]

treatment(iron_pills, [iron_deficiency_anemia]).
treatment(diet, [iron_deficiency_anemia, diabetes_type_1, diabetes_type_2, gestational_diabetes, neurally_mediated_hypotension, postprandial_hypotension]).
treatment(blood_transfusion, [iron_deficiency_anemia, pernicious_anemia]).
treatment(vitamin_b12_injections, [pernicious_anemia]).
treatment(insulin_injections, [diabetes_type_1, diabetes_type_2]).
treatment(blood_sugar_monitoring, [diabetes_type_1, diabetes_type_2, gestational_diabetes]).
treatment(excercise, [diabetes_type_1, diabetes_type_2, gestational_diabetes, isolated_systolic_hypertension, resistant_hypertension_hypertension] ).
treatment(blood_thiners, [thrombus_blood_clot, embolus_blood_clot]).
treatment(surgery, [thrombus_blood_clot, embolus_blood_clot, lymphocytic_leukemia, myeloid_leukemia, hodgkin_lymphoma, solitary_plasmacytoma]).
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
creatinine_level(nikola,low).
calcium_level(nikola,low).
albumin_level(nikola,low).
ldh_level(nikola,low).
person(vanja).
age(vanja,56).
activity(vanja,active).
smoker(vanja,no).
pregnant(vanja,no).
genetics(vanja,hypotension).
genetics(vanja,hemophilia).
red_cell_count(vanja,low).
platelets_count(vanja,low).
white_cell_count(vanja,low).
