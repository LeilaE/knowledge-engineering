person(stasa).
person(milica).
person(ana).
person(masa).
person(aleksandar).
person(petar).
person(mihajlo).
person(nikola).
person(srdjan).

% additional test for anemia that confirmes pernicious anemia if
% deficient(normal)
b12_check(stasa, deficient).
folic_acid_check(stasa, normal).
number_of_neutrophils(stasa, deficent).

% additional test for anemia that confirmes iron-deficinecy anemia if
% decreased(normal)
hemoglobin_check(stasa, normal).
iron_check(stasa, normal).

% additional test for diabetes that confirmes iron-deficinecy anemia if
% decreased(normal)
blood_sugar_level(mihajlo, high).

% utice na anemiju (65+), blood cloth (60+)
age(stasa, 22).
age(milica, 23).
age(ana, 35).
age(masa, 51).
age(aleksandar, 40).
age(petar, 32).
age(mihajlo, 55 ).
age(nikola, 76).
age(srdjan, 66).

% utice na dijabetes, high_blood_pressure
activity(stasa, inactive).
activity(milica, active).
activity(ana, active).
activity(masa, active).
activity(aleksandar, inactive).
activity(petar, inactive).
activity(mihajlo, active).
activity(nikola, inactive).
activity(srdjan, active).

% utice na blood cloth
smoker(stasa, no).
smoker(milica, yes).
smoker(ana, yes).
smoker(masa, no).
smoker(aleksandar, no).
smoker(petar, yes).
smoker(mihajlo, yes).
smoker(nikola, yes).
smoker(srdjan, no).

genetics(stasa, [high_blood_pressure]).
genetics(milica, []).
genetics(ana, [diabetes]).
genetics(masa, [anemia]).
genetics(aleksandar, []).
genetics(petar, []).
genetics(mihajlo, [high_blood_pressure]).
genetics(nikola, []).
genetics(srdjan, []).

pregnant(ana, yes).
pregnant(masa, yes).
