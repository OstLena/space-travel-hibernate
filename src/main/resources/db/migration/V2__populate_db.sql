insert into client(name)
values
    ('Gregor'),
    ('Max'),
    ('Helen'),
    ('Robert'),
    ('Rose'),
    ('Debora'),
    ('Rhone'),
    ('Steve'),
    ('Mike'),
    ('Lois'),
    ('Naomi');

    insert into planet (id, name)
values
    ('EAR', 'Earth'),
    ('MAR', 'Mars'),
    ('MERC', 'Mercury'),
    ('VENS', 'Venus'),
    ('JUPT', 'Jupiter'),
    ('SATR', 'Saturn'),
    ('URNS', 'Uranus'),
    ('NEPT', 'Neptune');

insert into ticket (created_at, client_id, from_planet_id, to_planet_id)
values
    (parsedatetime('01-01-2000 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 1, 'EAR', 'MAR'),
    (parsedatetime('30-11-2018 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 2, 'MAR', 'MERC'),
    (parsedatetime('13-02-2019 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 3, 'MERC', 'VENS'),
    (parsedatetime('21-06-2017 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 4, 'VENS', 'JUPT'),
    (parsedatetime('30-07-2022 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 5, 'JUPT', 'SATR'),
    (parsedatetime('12-01-2021 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 4, 'SATR', 'URNS'),
    (parsedatetime('03-10-2000 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 5, 'URNS', 'NEPT'),
    (parsedatetime('29-04-2015 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 6, 'NEPT', 'EAR'),
    (parsedatetime('06-03-2000 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 7, 'EAR', 'URNS'),
    (parsedatetime('08-06-2023 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 8, 'URNS', 'MAR'),
    (parsedatetime('17-12-2018 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 9, 'MAR', 'SATR'),
    (parsedatetime('17-12-2022 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 10, 'SATR','MERC'),
    (parsedatetime('06-02-2022 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 4, 'MERC', 'JUPT'),
    (parsedatetime('09-05-2000 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 8, 'JUPT', 'EAR'),
    (parsedatetime('10-01-2020 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 6, 'EAR', 'VENS'),
    (parsedatetime('11-03-2019 16:47:52.69', 'dd-MM-yyyy HH:mm:ss.SS'), 1, 'VENS', 'MAR');


