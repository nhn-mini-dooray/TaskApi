INSERT INTO `nhn_academy_43`.`projects` (project_name, account_id, project_status_id)
VALUES ('미니 두레이', 1, 1), ('증명서 발급', 2, 1), ('항공권 예약 시스템', 3, 1);

INSERT INTO `nhn_academy_43`.`members` (account_id, project_id, role_id)
VALUES (1, 1, 1), (2, 2, 1), (3, 1, 1),
       (4, 1, 2), (5, 2, 2), (6, 3, 2);