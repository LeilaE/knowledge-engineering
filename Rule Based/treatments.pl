% Treatment suggestions
% Name of the treatment and a list of diseases it is suggested for
% treatment_for(disease_name, [tre1, tre2, tre3...])
% treatment_for(disease_name, X) -> X is treatments

treatment(iron_pills).
treatment(blood_transfusion).
treatment(anticoagulants).
treatment(surgery).
treatment(insulin).
treatment(diet).
treatment(high_blood_pressure_medicine).

treatment_for(anemia, [iron_pills, blood_transfusion]).
treatment_for(blood_cloth, [anticoagulants, surgery]).
treatment_for(diabetes,[insulin, diet]).
treatment_for(high_blood_pressure, [diet, high_blood_pressure_medicine]).
