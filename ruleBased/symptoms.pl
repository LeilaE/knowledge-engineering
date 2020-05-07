% Symptom and disease knowledge base
% Name of the disease and a list of associated symptoms
% disease(name, [sym1, sym2, sym3...])
% suggest_diagnosis(X, symptoms(person_name, [sym1, sym2, sym3...])) -> X = suggested_disease

symptoms(person, list_of_symptoms).

disease(anemia,[fatigue, weakness, pale_skin, breathing_difficulties]).
disease(blood_cloth,[bleeding, swelling, change_in_color, cramps, bruising]).
disease(diabetes,[fatigue, increased_thirst, headache, troube_concentrating, blurred_vision, frequent_peeing, weight_loss]).
disease(high_blood_pressure, [irregular_heartbeat, pounding_in_the_neck, pounding_in_the_ears, vision_problems, headache, chest_pain, breathing_difficulties]).

contains(S,[]).
contains(S,[H|T]) :- member(H,S), contains(S,T).

suggest_diagnosis( symptoms(X, S), B) :- disease(B,S2), contains(S2,S), person(X).

% symptoms
symptom(fatigue).
symptom(weakness).
symptom(pale_skin).
symptom(breathing_difficulties).
symptom(bleeding).
symptom(swelling).
symptom(change_in_color).
symptom(cramps).
symptom(bruising).
symptom(increased_thirst).
symptom(headache).
symptom(troube_concentrating).
symptom(blurred_vision).
symptom(frequent_peeing).
symptom(weight_loss).
symptom(irregular_heartbeat).
symptom(pounding_in_the_neck).
symptom(pounding_in_the_ears).
symptom(vision_problems).
symptom(headache).
symptom(chest_pain).
