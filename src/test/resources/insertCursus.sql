# insert into cursussen(naam)
# values('testGroep');
# insert into groepscursussen(id, van, tot) values
# ((select id from cursussen where naam = 'testGroep'), '2018-01-01', '2018-01-01');
#
# insert into cursussen(naam)
# values('testIndividueel');
# insert into individuelecursussen(id, duurtijd) VALUES
# ((select id from cursussen where naam = 'testIndividueel'), 3);

insert into groepscursussen(id, naam, van, tot)
values (uuid_to_bin(uuid()), 'testGroep', '2018-01-01', '2018-01-01');

insert into individuelecursussen(id, naam, duurtijd)
values (uuid_to_bin(uuid()), 'testIndividueel', 3);