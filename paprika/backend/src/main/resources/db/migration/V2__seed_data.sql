--
-- PostgreSQL database dump
--


-- Dumped from database version 18.4 (Debian 18.4-1.pgdg13+1)
-- Dumped by pg_dump version 18.4 (Debian 18.4-1.pgdg13+1)


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users VALUES (101, '1234@naver.com', '$2a$10$Y/BBZEmtAZvIlVVAOUCOBu6lcFkhuMGogxqCs2o.97vYNDn8IYq62', '이동준', NULL, 'USER', 'LOCAL', NULL, true, '2026-07-03 08:44:59.680472+00', '2026-07-03 08:44:59.680472+00');
INSERT INTO public.users VALUES (102, 'cfu344@gmail.com', NULL, '민동현6b3a', 'https://lh3.googleusercontent.com/a/ACg8ocL5oxO24qgzx1Qx5qEQZTvqk31yBT6u_O1T2jq9w7qZ9gBxyJeF=s96-c', 'USER', 'GOOGLE', '102790726509231890134', true, '2026-07-03 08:46:31.785977+00', '2026-07-03 08:46:31.785977+00');
INSERT INTO public.users VALUES (103, '147284225@github.paprika', NULL, 'hyun326', 'https://avatars.githubusercontent.com/u/147284225?v=4', 'USER', 'GITHUB', '147284225', true, '2026-07-03 08:46:58.256331+00', '2026-07-03 08:46:58.256331+00');
INSERT INTO public.users VALUES (100, 'cfu3455@naver.com', '$2a$10$znCJHPSHSIPMGmnXROuTfOjcSJyd8fzvLJnrfx1dWMcha/0Csdc.a', '민동현', 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783068543/paprika/profiles/xbb94fphizhjh2poez0o.jpg', 'USER', 'LOCAL', NULL, false, '2026-07-03 08:44:46.351543+00', '2026-07-03 08:51:06.690739+00');
INSERT INTO public.users VALUES (104, 'cfu344@naver.com', NULL, '민동현2d6d', 'https://ssl.pstatic.net/static/pwe/address/img_profile.png', 'USER', 'NAVER', 'd0aDPQOYE8gS3A72bmS00PKi1uL1xtwaQlsf6Of1gr0', true, '2026-07-03 09:04:10.209382+00', '2026-07-03 09:04:10.209382+00');
INSERT INTO public.users VALUES (105, 'cfu3444@gmail.com', '$2a$10$bW/VUlWERGxs2VgL70ZqWuSrFz.jxEZyyHkAxaNzvuf7upDyYcwri', '민동현123', NULL, 'USER', 'LOCAL', NULL, true, '2026-07-03 09:09:31.961473+00', '2026-07-03 09:09:31.961473+00');
INSERT INTO public.users VALUES (106, 'mcha5888@gmail.com', '$2a$10$obDNJ3G69vFL0QsGy8w37umtB2OW2NLucxyhsvdOxgt4mEmnSIQ6W', '장인호', NULL, 'USER', 'LOCAL', NULL, true, '2026-07-03 09:18:33.121494+00', '2026-07-03 09:18:33.121494+00');


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.post VALUES (1, '2026-07-03 08:53:14.689882+00', '2026-07-03 08:53:14.689882+00', true, 'ELECTRONICS', '새상품 입니다. ', 700000.00, 0, 0, 'SELLING', 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783068793/paprika/products/ncbyjayvoztg4fwxd1kj.jpg', '갤럭시 s26 새상품', 101, 0);
INSERT INTO public.post VALUES (4, '2026-07-03 08:57:30.210306+00', '2026-07-03 09:14:19.594483+00', true, 'HOME', '한번 세척하면 새 상품입니다. ', 500000.00, 0, 0, 'SOLD', 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783069049/paprika/products/gftcrnah7am57wz3yek5.jpg', '쇼파 팝니다 ', 101, 0);
INSERT INTO public.post VALUES (2, '2026-07-03 08:54:59.209612+00', '2026-07-03 09:16:31.935453+00', true, 'BOOKS', '민동현이 걸어온 길 사십쇼 ', 20000.00, 0, 0, 'RESERVED', 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783068897/paprika/products/yaazbny15988lvsombkt.jpg', '민동현 자서전 10권 팝니다', 101, 0);
INSERT INTO public.post VALUES (3, '2026-07-03 08:56:31.827956+00', '2026-07-03 09:17:15.461328+00', true, 'FASHION', '사용감 있습니다 ', 100000.00, 0, 0, 'SELLING', 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783068990/paprika/products/vgio1n3xio9ckbz2ssxt.jpg', '블레이저 팝니다', 101, 0);
INSERT INTO public.post VALUES (5, '2026-07-03 08:58:24.724683+00', '2026-07-03 09:18:58.921509+00', true, 'KIDS', '애기가 커서 다른분 쓰시라고 팝니다.', 200000.00, 0, 0, 'SOLD', 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783069103/paprika/products/oa1u3igvwf5lid6r4gzb.jpg', '유모차 팝니다', 101, 0);


--
-- Data for Name: chat_rooms; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.chat_rooms VALUES (1, 3, 104, 101, '2026-07-03 09:06:24.20213+00', NULL);
INSERT INTO public.chat_rooms VALUES (2, 1, 102, 101, '2026-07-03 09:06:48.530329+00', NULL);
INSERT INTO public.chat_rooms VALUES (3, 2, 103, 101, '2026-07-03 09:07:05.054378+00', NULL);
INSERT INTO public.chat_rooms VALUES (4, 4, 105, 101, '2026-07-03 09:09:48.011509+00', NULL);


--
-- Data for Name: chat_messages; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.chat_messages VALUES (1, 1, 104, '네이버', '2026-07-03 09:06:29.442514+00');
INSERT INTO public.chat_messages VALUES (2, 2, 102, '구글', '2026-07-03 09:06:51.352472+00');
INSERT INTO public.chat_messages VALUES (3, 3, 103, '깃', '2026-07-03 09:07:07.978582+00');
INSERT INTO public.chat_messages VALUES (4, 4, 105, '로컬', '2026-07-03 09:09:52.190701+00');


--
-- Data for Name: chat_read; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.chat_read VALUES (1, 104, 0, '2026-07-03 09:06:24.505204+00');
INSERT INTO public.chat_read VALUES (1, 101, 1, '2026-07-03 09:06:31.595023+00');
INSERT INTO public.chat_read VALUES (2, 102, 0, '2026-07-03 09:06:48.820734+00');
INSERT INTO public.chat_read VALUES (2, 101, 2, '2026-07-03 09:06:53.626118+00');
INSERT INTO public.chat_read VALUES (3, 103, 0, '2026-07-03 09:07:05.331887+00');
INSERT INTO public.chat_read VALUES (3, 101, 3, '2026-07-03 09:07:11.03742+00');
INSERT INTO public.chat_read VALUES (4, 101, 4, '2026-07-03 09:09:59.942485+00');
INSERT INTO public.chat_read VALUES (4, 105, 4, '2026-07-03 09:11:18.332048+00');


--
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.transactions VALUES (1, 4, 101, 105, 'DELIVERY', 'COMPLETED', 500000.00, 0.00, 500000.00, NULL, '2026-07-03 09:13:47.948429+00', '2026-07-03 09:14:19.594326+00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CASH');
INSERT INTO public.transactions VALUES (2, 2, 101, 103, 'DIRECT', 'AGREED', 20000.00, 0.00, 20000.00, NULL, '2026-07-03 09:16:23.865125+00', '2026-07-03 09:16:23.865125+00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.transactions VALUES (3, 3, 101, 104, 'DIRECT', 'CANCELLED', 100000.00, 0.00, 100000.00, NULL, '2026-07-03 09:17:02.637383+00', '2026-07-03 09:17:15.461167+00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.transactions VALUES (4, 5, 101, 106, 'DELIVERY', 'COMPLETED', 200000.00, 0.00, 200000.00, NULL, '2026-07-03 09:18:51.636156+00', '2026-07-03 09:18:58.921295+00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CASH');


--
-- Data for Name: delivery_transactions; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.delivery_transactions VALUES (1, NULL, 'READY');
INSERT INTO public.delivery_transactions VALUES (4, NULL, 'READY');


--
-- Data for Name: direct_transactions; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.direct_transactions VALUES (2, '대한민국 서울특별시 서초구 서초동 1321-40', '2026-07-22 04:06:00+00', 'CONFIRMED', '2026-07-03 09:16:23.871706+00', NULL, NULL);
INSERT INTO public.direct_transactions VALUES (3, '대한민국 서울특별시 서초구 서초동 1322-7', '2026-07-23 04:05:00+00', 'CONFIRMED', '2026-07-03 09:17:02.641899+00', NULL, NULL);


--
-- Data for Name: manner_temperatures; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.manner_temperatures VALUES (101, 52, '보통', 1, '2026-07-03 09:14:36.651034+00');


--
-- Data for Name: password_reset_tokens; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: post_image; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.post_image VALUES (1, true, NULL, 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783068793/paprika/products/ncbyjayvoztg4fwxd1kj.jpg', 1);
INSERT INTO public.post_image VALUES (2, true, NULL, 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783068897/paprika/products/yaazbny15988lvsombkt.jpg', 2);
INSERT INTO public.post_image VALUES (3, true, NULL, 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783068990/paprika/products/vgio1n3xio9ckbz2ssxt.jpg', 3);
INSERT INTO public.post_image VALUES (4, true, NULL, 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783068990/paprika/products/muehr6qqtszxbt3r53ls.jpg', 3);
INSERT INTO public.post_image VALUES (5, true, NULL, 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783068990/paprika/products/v0rzkv8jvowdb2smfaeb.jpg', 3);
INSERT INTO public.post_image VALUES (6, true, NULL, 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783069049/paprika/products/gftcrnah7am57wz3yek5.jpg', 4);
INSERT INTO public.post_image VALUES (7, true, NULL, 'https://res.cloudinary.com/dp1jjtgwk/image/upload/v1783069103/paprika/products/oa1u3igvwf5lid6r4gzb.jpg', 5);


--
-- Data for Name: post_price_history; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: refresh_tokens; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.refresh_tokens VALUES (12, 101, 'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE3ODMwNjkxOTMsImV4cCI6MTc4MzY3Mzk5M30.9cnUCN4oJt_9qAi1aecgNikw22VbPhgACCnel5dmpdnVb7ynX1jzdXSHu6rXc_Tz', '2026-07-03 08:59:53.376458+00', '2026-07-10 08:59:53.376126+00');
INSERT INTO public.refresh_tokens VALUES (22, 104, 'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxMDQiLCJpYXQiOjE3ODMwNzAzNzAsImV4cCI6MTc4MzY3NTE3MH0.9f0sjmoi6rvYqPwqwdVm3H6Cv6zlPRQrXPd9V_tro2wRhMlGerXa6b6Wmw-M5E5a', '2026-07-03 09:19:30.724855+00', '2026-07-10 09:19:30.724533+00');


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.reviews VALUES (1, 1, 105, 101, 5, 2, '잘구매했습니다', '2026-07-03 09:14:36.6427+00', '2026-07-03 09:14:36.636792+00');


--
-- Data for Name: wish_list; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.wish_list VALUES (1, '2026-07-03 09:19:38.408598', 1, 104);


--
-- Name: chat_messages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.chat_messages_id_seq', 4, true);


--
-- Name: chat_rooms_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.chat_rooms_id_seq', 4, true);


--
-- Name: password_reset_tokens_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.password_reset_tokens_id_seq', 1, false);


--
-- Name: post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.post_id_seq', 5, true);


--
-- Name: post_image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.post_image_id_seq', 7, true);


--
-- Name: post_price_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.post_price_history_id_seq', 1, false);


--
-- Name: refresh_tokens_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.refresh_tokens_id_seq', 22, true);


--
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.reviews_id_seq', 1, true);


--
-- Name: transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.transactions_id_seq', 4, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.users_id_seq', 106, true);


--
-- Name: wish_list_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.wish_list_id_seq', 1, true);


--
-- PostgreSQL database dump complete
--


