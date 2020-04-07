% Treatment suggestions
% Name of the treatment and a list of diseases it is suggested for
% treatment_for(disease_name, [tre1, tre2, tre3...])
% treatment_for(disease_name, X) -> X is treatments



treatment(iron_pills, [iron-deficiency_anemia]).
treatment(diet, [iron-deficiency_anemia, diabetes_type_1, diabetes_type_2, gestational_diabetes]).
treatment(blood_transfusion, [iron-deficiency_anemia, pernicious_anemia]).
treatment(vitamin_b12_injections, [pernicious_anemia]).
treatment(insulin_injections, [diabetes_type_1, diabetes_type_2]).
treatment(blood_sugar_monitoring, [diabetes_type_1, diabetes_type_2, gestational_diabetes]).
treatment(excercise, [diabetes_type_1, diabetes_type_2, gestational_diabetes] ).

contains(S,[]).
contains(S,[H|T]) :- member(H,S), contains(S,T).

treatment_for(X, T) :- treatment(T, L), contains(L, X).
