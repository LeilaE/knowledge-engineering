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
