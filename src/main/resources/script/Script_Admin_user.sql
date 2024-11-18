
INSERT INTO dbo.user_person (created_by, date_created, date_modified, modified_by, email, firstname, lastname, password, status, username)
VALUES (123, getdate(), getdate(), NULL, 'srosales@stacktrace.com', 'Sebastian', 'Rosales', '$2a$10$UvDwGEYLsRN/p/KjwngodOQNTLkHPRlly0piWMPpOczVKBLnP35iy', 'ACTIVE', 'Admin')
GO

INSERT INTO dbo.role ([enable], created_by, date_created, date_modified, modified_by, code, description, name)
VALUES (1, 1, getdate(), NULL, NULL, 'ROLE_ADMIN', 'Usuario admin', 'ADMIN')

INSERT INTO dbo.user_person_has_role ([enable], role_id, user_person_id, created_by, date_created, date_modified, modified_by)
VALUES (1, 1, 1, 1, getdate(), NULL, NULL)