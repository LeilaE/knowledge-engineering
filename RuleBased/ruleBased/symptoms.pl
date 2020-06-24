% Symptom and disease knowledge base
% Name of the disease and a list of associated symptoms
% disease(name, [sym1, sym2, sym3...])
% suggest_diagnosis(X, symptoms(person_name, [sym1, sym2, sym3...])) -> X = suggested_disease

symptoms(person, list_of_symptoms).

disease(anemia,[fatigue, weakness, pale_skin, breathing_difficulties]).
disease(diabetes,[fatigue, increased_thirst, headache, troube_concentrating, blurred_vision, frequent_peeing, weight_loss]).
disease(blood_clot,[bleeding, swelling, change_in_color, cramps, bruising]).
disease(hypertension, [irregular_heartbeat, pounding_in_the_neck, pounding_in_the_ears, vision_problems, headache, chest_pain, breathing_difficulties]).
disease(hypotension, [dizziness, lightheadedness, fatigue, vision_problems, nausea, lack_of_concentration]).
disease(leukemia, [chills, fever, fatigue, weakness, swollen_lymph_nodes, enlarged_liver, weight_loss, nosebleeds, bone_pain, sweating]).
disease(lymphoma, [fever, fatigue, sweating, shortness_of_breath, itchy_skin, weight_loss]).
disease(hemophilia, [bruising, excessive_bleeding, bloody_urine, bloody_stool, nosebleeds, unexplained_irritability]).
disease(thrombocytosis, [headache, dizziness, lightheadedness, weakness, numb_limbs]).
disease(myeloma, [spine_pain, bone_pain, chest_pain, nausea, appetite_loss, fatigue, mental_confusion, weight_loss, numb_limbs, increased_thirst]).


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
symptom(dizziness).
symptom(lightheadedness).
symptom(vision_problems).
symptom(nausea).
symptom(lack_of_concentration).
symptom(chills).
symptom(fever).
symptom(swollen_lymph_nodes).
symptom(enlarged_liver).
symptom(nosebleeds).
symptom(bone_pain).
symptom(sweating).
symptom(shortness_of_breath).
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

