-- SEQUENCE: public.employee_sequence

CREATE SEQUENCE public.employee_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


-- SEQUENCE: public.employee_session_sequence

CREATE SEQUENCE public.employee_session_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


-- SEQUENCE: public.meeting_room_booking_sequence

CREATE SEQUENCE public.meeting_room_booking_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


-- SEQUENCE: public.meeting_room_sequence

CREATE SEQUENCE public.meeting_room_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- SEQUENCE: public.permission_sequence

CREATE SEQUENCE public.permission_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- SEQUENCE: public.role_sequence

CREATE SEQUENCE public.role_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


-- Table: public.employee

CREATE TABLE IF NOT EXISTS public.employee
(
    employee_id BIGINT NOT NULL,
    full_name CHARACTER VARYING(255) NOT NULL,
    employee_code CHARACTER VARYING(255) UNIQUE NOT NULL,
    email CHARACTER VARYING(255) UNIQUE NOT NULL,
    password CHARACTER VARYING(255) NOT NULL,
    phone_number CHARACTER VARYING(20),
    is_active BOOLEAN,
    created_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
);

-- Table: public.role


CREATE TABLE IF NOT EXISTS public.role
(
    role_id BIGINT NOT NULL,
    name character varying(255) UNIQUE NOT NULL,
    description CHARACTER VARYING(255),
    created_on TIMESTAMP WITH TIME ZONE  DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP WITH TIME ZONE  DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT role_pkey PRIMARY KEY (role_id)
);

-- Table: public.permission

CREATE TABLE IF NOT EXISTS public.permission
(
    permission_id BIGINT NOT NULL,
    name character varying(255) UNIQUE NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    created_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT permission_pkey PRIMARY KEY (permission_id)
);

-- Table: public.role_permission

CREATE TABLE IF NOT EXISTS public.role_permission
(
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    CONSTRAINT role_permission_pkey PRIMARY KEY (role_id, permission_id),
    CONSTRAINT role_id_fkey FOREIGN KEY (role_id)
        REFERENCES public.role (role_id) MATCH SIMPLE
        ON DELETE CASCADE,
    CONSTRAINT permission_id_fkey FOREIGN KEY (permission_id)
        REFERENCES public.permission (permission_id) MATCH SIMPLE
        ON DELETE CASCADE
);

-- Table: public.employee_roles

CREATE TABLE IF NOT EXISTS public.employee_roles
(
    employee_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    CONSTRAINT employee_roles_pkey PRIMARY KEY (employee_id, role_id),
    CONSTRAINT employee_id_fkey FOREIGN KEY (employee_id)
        REFERENCES public.employee (employee_id) MATCH SIMPLE
        ON DELETE CASCADE,
    CONSTRAINT role_id_fkey FOREIGN KEY (role_id)
        REFERENCES public.role (role_id) MATCH SIMPLE
        ON DELETE CASCADE
);


-- Table: public.employee_session

CREATE TABLE IF NOT EXISTS public.employee_session
(
    employee_session_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    created_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT employee_session_pkey PRIMARY KEY (employee_session_id),
    CONSTRAINT employee_id_fkey FOREIGN KEY (employee_id)
        REFERENCES public.employee (employee_id) MATCH SIMPLE
        ON DELETE CASCADE
);


-- Table: public.meeting_room

CREATE TABLE IF NOT EXISTS public.meeting_room
(
    meeting_room_id INTEGER NOT NULL,
    name CHARACTER VARYING(255) NOT NULL,
    room_code CHARACTER VARYING(255) UNIQUE NOT NULL,
    description CHARACTER VARYING(255),
    amenity jsonb,
    capacity INTEGER NOT NULL,
    floor_number CHARACTER VARYING(255) NOT NULL,
    is_active BOOLEAN,
    location CHARACTER VARYING(255) NOT NULL,
    created_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT meeting_room_pkey PRIMARY KEY (meeting_room_id)
);


-- Table: public.meeting_room_booking

CREATE TABLE IF NOT EXISTS public.meeting_room_booking
(
    meeting_room_booking_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    meeting_room_id INTEGER NOT NULL,
    meeting_title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description CHARACTER VARYING(255),
    attendee_count INTEGER NOT NULL,
    booking_status CHARACTER VARYING(255) NOT NULL,
    start_time TIMESTAMP WITH TIME ZONE NOT NULL,
    end_time TIMESTAMP WITH TIME ZONE NOT NULL,
    created_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT meeting_room_booking_pkey PRIMARY KEY (meeting_room_booking_id),
    CONSTRAINT employee_id_fkey FOREIGN KEY (employee_id)
        REFERENCES public.employee (employee_id) MATCH SIMPLE
        ON DELETE CASCADE,
    CONSTRAINT meeting_room_id_fkey FOREIGN KEY (meeting_room_id)
        REFERENCES public.meeting_room (meeting_room_id) MATCH SIMPLE
        ON DELETE CASCADE
);


