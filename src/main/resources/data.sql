
INSERT INTO `nhn_academy_43`.`project_status` (`project_status_name`)
VALUES ('ACTIVE'), ('INACTIVE'), ('COMPLETE');

INSERT INTO `nhn_academy_43`.`projects` (`account_id`, `project_status_id`)
VALUES (1, 1), (2, 2), (3, 1);