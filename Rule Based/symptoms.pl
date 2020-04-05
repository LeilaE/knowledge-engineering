import('people.pl').


b12_check(stasa, deficient).
b12_check(petar, normal).

folic_acid_check(petar, deficient).
folic_acid_check(stasa, normal).

blood_sugar_level(stasa, 200).
blood_sugar_level(petar, 150).


symptoms(person, list_of_symptoms).



disease(anemia,[fatigue, weakness, pale_skin, breathing_difficulties]).
disease(blood_cloth,[bleeding, swelling, change_in_color, cramps, bruising]).
disease(diabetes,[fatigue, increased_thirst, headache, troube_concentrating, blurred_vision, frequent_peeing, weight_loss]).
disease(high_blood_pressure, [irregular_heartbeat, pounding_in_the_neck, pounding_in_the_ears, vision_problems, headache, chest_pain, breathing_difficulties]).


contains(S,[]).
contains(S,[H|T]) :- member(H,S), contains(S,T).

initial_guess(B, symptoms(X, S)) :- disease(B,S2), contains(S2,S).

additional_test(symptoms(X, S), hemoglobin_check) :-
    disease(anemia, S2), contains(S2, S).

additional_test(symptoms(X, S), iron_check) :-
    disease(anemia, S2), contains(S2, S).

additional_test(symptoms(X, S), b12_check) :-
    disease(anemia, S2), contains(S2, S).

additional_test(symptoms(X, S), folic_acid_check) :-
    disease(anemia, S2), contains(S2, S).

additional_test(symptoms(X, S), number_of_neutrophils) :-
    disease(anemia, S2), contains(S2, S).

additional_test(symptoms(X, S), blood_sugar_level) :-
    disease(diabetes, S2), contains(S2, S).


diagnosis(X, pernicious_anemia) :-
    b12_check(X, P1), P1 = deficient;
    folic_acid_check(X, P2), P2 = deficient.


diagnosis(X, diabetes) :-
    blood_sugar_level(X,P1), P1 > 197,
    genetics(X, Y), member(diabetes, Y).



