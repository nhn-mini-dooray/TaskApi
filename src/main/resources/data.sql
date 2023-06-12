INSERT INTO `nhn_academy_43`.`projects` (project_name, account_id, project_status_id)
VALUES ('미니 두레이', 1, 1),
       ('증명서 발급', 2, 1),
       ('항공권 예약 시스템', 3, 1);

INSERT INTO `nhn_academy_43`.`project_status` (`project_status_name`)
VALUES ('ACTIVE'),
       ('INACTIVE'),
       ('COMPLETE');

-- Insert data into project_status
INSERT INTO project_status (project_status_name)
VALUES ('NotStarted'),
       ('InProgress'),
       ('Done');

-- Insert data into role
INSERT INTO role (role_name)
VALUES ('ADMIN'),
       ('MEMBER');


-- Insert data into members
INSERT INTO members (account_id, project_id, role_id)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 2, 1);

-- Insert data into mile_stones
INSERT INTO mile_stones (project_id, mile_stone_name, mile_stone_start_date, mile_stone_end_date)
VALUES (1, 'Design', '2023-06-01', '2023-06-07'),
       (1, 'Development', '2023-06-08', '2023-06-14'),
       (2, 'Testing', '2023-06-15', '2023-06-21');

-- Insert data into tasks
INSERT INTO tasks (project_id, mile_stone_id, task_name, task_content)
VALUES (1, 1, 'Task 1', 'Task 1 description'),
       (1, 1, 'Task 2', 'Task 2 description'),
       (1, 2, 'Task 3', 'Task 3 description'),
       (2, 3, 'Task 4', 'Task 4 description');

-- Insert data into tags
INSERT INTO tags (project_id, tag_name)
VALUES (1, 'Important'),
       (1, 'Urgent'),
       (2, 'Bug');

-- Insert data into task_tag
INSERT INTO task_tag (task_id, tag_id)
VALUES (1, 1),
       (1, 2),
       (3, 1),
       (4, 3);

-- Insert data into comments
INSERT INTO comments (comment_id, task_id, account_id, comment_content)
VALUES (1, 1, 1, 'Comment 1'),
       (2, 1, 2, 'Comment 2'),
       (3, 2, 3, 'Comment 3');