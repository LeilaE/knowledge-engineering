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
symptom(trouble_concentrating).
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

