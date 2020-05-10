CREATE TABLE user_role (
    id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(20)
);

CREATE TABLE issue_state (
    id SERIAL PRIMARY KEY,
    state CHARACTER VARYING(20)
);

CREATE TABLE issue_type (
    id SERIAL PRIMARY KEY,
    type CHARACTER VARYING(20)
);

CREATE TABLE rights (
    id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(20),
    allowed BOOLEAN
);

CREATE TABLE member (
    id SERIAL PRIMARY KEY,
    login CHARACTER VARYING(20),
    password CHARACTER VARYING(25),
    role INTEGER REFERENCES user_role(id)
);

CREATE TABLE role_rights (
    id SERIAL PRIMARY KEY,
    role_id INTEGER REFERENCES user_role(id),
    right_id INTEGER REFERENCES rights(id)
);

CREATE TABLE issues (
    id SERIAL PRIMARY KEY,
    summary CHARACTER VARYING(250),
    description CHARACTER VARYING(1000),
    author INTEGER REFERENCES member(id),
    state INTEGER REFERENCES issue_state(id),
    type INTEGER REFERENCES issue_type(id)
);

CREATE TABLE issue_comments (
    id SERIAL PRIMARY KEY,
    comment CHARACTER VARYING(1000),
    author INTEGER REFERENCES member(id),
    time TIMESTAMP(0),
    issue_id INTEGER REFERENCES issues(id)
);

CREATE TABLE issue_attachments (
    id SERIAL PRIMARY KEY,
    issue_id INTEGER REFERENCES issues(id),
    attachment_data BYTEA
);