-- Adding default role and permissions

INSERT INTO public.role(role_id,name, description, created_on, updated_on)
VALUES
(nextval('role_sequence'),'ADMIN', 'Admin Role', now(), now()),
(nextval('role_sequence'),'EMPLOYEE', 'Employee Role', now(), now());


INSERT INTO public.permission(permission_id, name, description, created_on, updated_on)
VALUES
(nextval('permission_sequence'), 'CREATE_EMP', 'Create Employee', now(), now()),
(nextval('permission_sequence'), 'UPDATE_EMP', 'Update Employee', now(), now()),
(nextval('permission_sequence'), 'DELETE_EMP', 'Delete Employee', now(), now()),
(nextval('permission_sequence'), 'VIEW_EMP', 'View Employee', now(), now()),
(nextval('permission_sequence'), 'SEARCH_EMP', 'Search Employee', now(), now());


INSERT INTO public.permission(permission_id, name, description, created_on, updated_on)
VALUES
(nextval('permission_sequence'), 'CREATE_MEETING_ROOM', 'Create Meeting Room', now(), now()),
(nextval('permission_sequence'), 'UPDATE_MEETING_ROOM', 'Update Meeting Room', now(), now()),
(nextval('permission_sequence'), 'DELETE_MEETING_ROOM', 'Delete Meeting Room', now(), now()),
(nextval('permission_sequence'), 'VIEW_MEETING_ROOM', 'View Meeting Room', now(), now()),
(nextval('permission_sequence'), 'SEARCH_MEETING_ROOM', 'Search Meeting Room', now(), now()),
(nextval('permission_sequence'), 'CREATE_ROOM_BOOKING', 'Book Meeting Room', now(), now()),
(nextval('permission_sequence'), 'UPDATE_ROOM_BOOKING', 'Update Booked Meeting Room', now(), now());


INSERT INTO public.role_permission(role_id, permission_id)
VALUES
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'CREATE_EMP')),
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'UPDATE_EMP')),
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'DELETE_EMP')),
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'VIEW_EMP')),
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'SEARCH_EMP')),
((SELECT role_id FROM role WHERE name = 'EMPLOYEE'), (SELECT permission_id FROM permission WHERE name = 'VIEW_EMP')),
((SELECT role_id FROM role WHERE name = 'EMPLOYEE'), (SELECT permission_id FROM permission WHERE name = 'UPDATE_EMP')),
((SELECT role_id FROM role WHERE name = 'EMPLOYEE'), (SELECT permission_id FROM permission WHERE name = 'SEARCH_EMP'));

INSERT INTO public.role_permission(role_id, permission_id)
VALUES
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'CREATE_MEETING_ROOM')),
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'UPDATE_MEETING_ROOM')),
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'DELETE_MEETING_ROOM')),
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'VIEW_MEETING_ROOM')),
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'SEARCH_MEETING_ROOM')),
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'CREATE_ROOM_BOOKING')),
((SELECT role_id FROM role WHERE name = 'ADMIN'), (SELECT permission_id FROM permission WHERE name = 'UPDATE_ROOM_BOOKING')),
((SELECT role_id FROM role WHERE name = 'EMPLOYEE'), (SELECT permission_id FROM permission WHERE name = 'VIEW_MEETING_ROOM')),
((SELECT role_id FROM role WHERE name = 'EMPLOYEE'), (SELECT permission_id FROM permission WHERE name = 'SEARCH_MEETING_ROOM')),
((SELECT role_id FROM role WHERE name = 'EMPLOYEE'), (SELECT permission_id FROM permission WHERE name = 'CREATE_ROOM_BOOKING')),
((SELECT role_id FROM role WHERE name = 'EMPLOYEE'), (SELECT permission_id FROM permission WHERE name = 'UPDATE_ROOM_BOOKING'));