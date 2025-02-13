--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    product_id integer NOT NULL,
    product_name character varying(40),
    product_value integer,
    user_id integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_deleted boolean DEFAULT false
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_product_id_seq OWNER TO postgres;

--
-- Name: products_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_product_id_seq OWNED BY public.products.product_id;


--
-- Name: sale_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sale_details (
    sale_detail_id integer NOT NULL,
    sale_id integer NOT NULL,
    product_id integer NOT NULL,
    product_name character varying(40),
    product_value integer NOT NULL,
    quantity integer NOT NULL,
    amount integer NOT NULL
);


ALTER TABLE public.sale_details OWNER TO postgres;

--
-- Name: sales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sales (
    sale_id integer NOT NULL,
    user_id integer NOT NULL,
    total_amount integer NOT NULL,
    receive_amount integer NOT NULL,
    change_amount integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.sales OWNER TO postgres;

--
-- Name: sales_details_sale_detail_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sales_details_sale_detail_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sales_details_sale_detail_id_seq OWNER TO postgres;

--
-- Name: sales_details_sale_detail_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sales_details_sale_detail_id_seq OWNED BY public.sale_details.sale_detail_id;


--
-- Name: sales_sale_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sales_sale_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sales_sale_id_seq OWNER TO postgres;

--
-- Name: sales_sale_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sales_sale_id_seq OWNED BY public.sales.sale_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    user_name character varying(40) NOT NULL,
    mail character varying(100) NOT NULL,
    password character varying(200) NOT NULL,
    role character varying(10) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: products product_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN product_id SET DEFAULT nextval('public.products_product_id_seq'::regclass);


--
-- Name: sale_details sale_detail_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_details ALTER COLUMN sale_detail_id SET DEFAULT nextval('public.sales_details_sale_detail_id_seq'::regclass);


--
-- Name: sales sale_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales ALTER COLUMN sale_id SET DEFAULT nextval('public.sales_sale_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (product_id, product_name, product_value, user_id, created_at, is_deleted) FROM stdin;
5	メカニカルキーボード	18000	3	2024-02-05 11:10:00	t
9	ゲーミングチェア	40000	5	2024-02-09 19:05:00	t
3	ワイヤレスイヤホン	15000	2	2024-02-03 09:15:00	t
4	ゲーミングマウス	7000	2	2024-02-04 16:20:00	t
6	外付けSSD 1TB	12000	3	2024-02-06 13:55:00	t
7	モニター 27インチ	30000	4	2024-02-07 17:30:00	t
8	USB-C ドック	9000	4	2024-02-08 08:40:00	t
10	VRヘッドセット	60000	5	2024-02-10 12:20:00	t
11	Apple	600	1	2025-02-06 18:19:18.420945	t
12	T-Shirt	600	7	2025-02-06 18:20:20.641699	t
13	アイス	400	11	2025-02-10 13:32:37.361032	f
14	チョコバナナ	300	11	2025-02-10 13:34:18.102019	f
1	たこ焼き	600	1	2025-02-05 17:30:07.278196	t
2	スマートフォン	80000	1	2024-02-02 14:45:00	t
\.


--
-- Data for Name: sale_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sale_details (sale_detail_id, sale_id, product_id, product_name, product_value, quantity, amount) FROM stdin;
9	11	13	アイス	400	1	400
10	11	14	チョコバナナ	300	1	300
11	12	14	チョコバナナ	300	7	2100
12	13	14	チョコバナナ	300	1	300
13	14	14	チョコバナナ	300	2	600
\.


--
-- Data for Name: sales; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sales (sale_id, user_id, total_amount, receive_amount, change_amount, created_at) FROM stdin;
11	11	700	1000	300	2025-02-10 14:03:51.784538
12	11	2100	2100	0	2025-02-10 14:04:54.53599
13	11	300	500	200	2025-02-10 14:14:23.137951
14	11	600	1000	400	2025-02-10 14:15:55.400951
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, user_name, mail, password, role, created_at) FROM stdin;
11	admin	admin@example.com	$2a$12$fwGhsdLbrJSDOuuj4sRjYOIKHsMpX09pD4YTScUMNT5MhvyBAdxFG	ADMIN	2025-02-10 12:09:28.930716
10	user	user@example.com	$2a$12$ZQqZprBo63GoLDX9uqL88uTDUr.LRRluk22AJM/nt5anVwj1W567u	ADMIN	2025-02-10 12:08:41.689866
\.


--
-- Name: products_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_product_id_seq', 14, true);


--
-- Name: sales_details_sale_detail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sales_details_sale_detail_id_seq', 13, true);


--
-- Name: sales_sale_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sales_sale_id_seq', 14, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 11, true);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- Name: sale_details sales_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_details
    ADD CONSTRAINT sales_details_pkey PRIMARY KEY (sale_detail_id);


--
-- Name: sales sales_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT sales_pkey PRIMARY KEY (sale_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- PostgreSQL database dump complete
--

