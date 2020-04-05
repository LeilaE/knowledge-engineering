% Examinations
% Lists exam_names and results that confirm diagnosis
% confirm_diagnosis(disease_name, exam_name, [par1, par2, par3...]) (par_name_high, par_name_normal, par_name_low)
% confirm_diagnosis?(disease_name, exam_name, [res1, res2, res3]) -> confirms disease_name

confirm_diagnosis(anemia, complete_blood_analysis, [rbc_low, wbc_normal, iron_normal, sugar_level_normal]).
confirm_diagnosis(high_blood_pressure, blood_pressure_test, [blood_pressure_high]).
confirm_diagnosis(diabetes, blood_sugar_test, [blood_sugar_high]).
