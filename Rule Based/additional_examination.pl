% Additional examination suggestions
% Name of examination and a list of symptoms it is suggested for
% additional_examination(exam_name, [sym1, sym2, sym3...])

additional_examination(complete_blood_analysis, [pale_skin, breathing_difficulties]).
additional_examination(blood_sugar_test, [increased_thirst, headache]).
additional_examination(blood_pressure_test, [chest_pain, breathing_difficulties]).

contains(S,[]).
contains(S,[H|T]) :- member(H,S), contains(S,T).

suggest_additional_examination(S,B) :- additional_examination(B,S2), contains(S2,S).
