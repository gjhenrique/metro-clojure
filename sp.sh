# Jundiaí Ruby
git checkout --orphan "Ruby"
git commit --allow-empty -m "Jundiaí"
# Várzea Paulista Ruby
git commit --allow-empty -m "Várzea Paulista"
# Campo Limpo Paulista Ruby
git commit --allow-empty -m "Campo Limpo Paulista"
# Botujuru Ruby
git commit --allow-empty -m "Botujuru"
# Francisco Morato Ruby
git commit --allow-empty -m "Francisco Morato"
# Baltazar Fidélis Ruby
git commit --allow-empty -m "Baltazar Fidélis"
# Franco da Rocha Ruby
git commit --allow-empty -m "Franco da Rocha"
# Caieiras Ruby
git commit --allow-empty -m "Caieiras"
# Perus Ruby
git commit --allow-empty -m "Perus"
# Vila Aurora Ruby
git commit --allow-empty -m "Vila Aurora"
# Jaraguá Ruby
git commit --allow-empty -m "Jaraguá"
# Vila Clarice Ruby
git commit --allow-empty -m "Vila Clarice"
# Pirituba Ruby
git commit --allow-empty -m "Pirituba"
# Piqueri Ruby
git commit --allow-empty -m "Piqueri"
# Amador Bueno Diamond
git checkout --orphan "Diamond"
git commit --allow-empty -m "Amador Bueno"
# Santa Rita Diamond
git commit --allow-empty -m "Santa Rita"
# Itapevi Diamond
git commit --allow-empty -m "Itapevi"
# Eng Cardoso Diamond
git commit --allow-empty -m "Eng Cardoso"
# Sagrado Coração Diamond
git commit --allow-empty -m "Sagrado Coração"
# Jandira Diamond
git commit --allow-empty -m "Jandira"
# Jardim Silveira Diamond
git commit --allow-empty -m "Jardim Silveira"
# Jardim Belval Diamond
git commit --allow-empty -m "Jardim Belval"
# Barueri Diamond
git commit --allow-empty -m "Barueri"
# Antonio João Diamond
git commit --allow-empty -m "Antonio João"
# Santa Terezinha Diamond
git commit --allow-empty -m "Santa Terezinha"
# Carapicuíba Diamond
git commit --allow-empty -m "Carapicuíba"
# Gen. Miguel Costa Diamond
git commit --allow-empty -m "Gen. Miguel Costa"
# Quitaúna Diamond
git commit --allow-empty -m "Quitaúna"
# Com. Sampaio Diamond
git commit --allow-empty -m "Com. Sampaio"
# Osasco Diamond Emerald
git commit --allow-empty -m "Osasco"
git branch -f "Emerald" HEAD
# Pres. Altino Diamond Emerald
git commit --allow-empty -m "Pres. Altino"
git branch -f "Emerald" HEAD
# Imperatriz - Leolpodina Diamond
git commit --allow-empty -m "Imperatriz - Leolpodina"
# Domingos de Moraes Diamond
git commit --allow-empty -m "Domingos de Moraes"
# Lapa Ruby Diamond
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Lapa" Ruby
git branch -f "Ruby" HEAD
# Água Branca Ruby
git checkout "Ruby"
git commit --allow-empty -m "Água Branca"
# Palmeiras - Barra Funda Red Ruby Diamond
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Palmeiras - Barra Funda" Diamond
git branch -f "Diamond" HEAD
git branch -f "Red" HEAD
# Mal. Deodoro Red
git checkout "Red"
git commit --allow-empty -m "Mal. Deodoro"
# Santa Cecília Red
git commit --allow-empty -m "Santa Cecília"
# Vila Madalena Green
git checkout --orphan "Green"
git commit --allow-empty -m "Vila Madalena"
# Sumaré Green
git commit --allow-empty -m "Sumaré"
# Clínicas Green
git commit --allow-empty -m "Clínicas"
# Ceasa Emerald
git checkout "Emerald"
git commit --allow-empty -m "Ceasa"
# Villa-Lobos - Jaguaré Emerald
git commit --allow-empty -m "Villa-Lobos - Jaguaré"
# Cidade Universitária Emerald
git commit --allow-empty -m "Cidade Universitária"
# Butantã Yellow
git checkout --orphan "Yellow"
git commit --allow-empty -m "Butantã"
# Pinheiros Yellow Emerald
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Pinheiros" Emerald
git branch -f "Emerald" HEAD
# Hebraica - Rebouças Emerald
git checkout "Emerald"
git commit --allow-empty -m "Hebraica - Rebouças"
# Cidade Jardim Emerald
git commit --allow-empty -m "Cidade Jardim"
# Vila Olímpia Emerald
git commit --allow-empty -m "Vila Olímpia"
# Berrini Emerald
git commit --allow-empty -m "Berrini"
# Morumbi Emerald
git commit --allow-empty -m "Morumbi"
# Granja Julieta Emerald
git commit --allow-empty -m "Granja Julieta"
# Capão Redondo Lilac
git checkout --orphan "Lilac"
git commit --allow-empty -m "Capão Redondo"
# Campo Limpo Lilac
git commit --allow-empty -m "Campo Limpo"
# Vila das Belezas Lilac
git commit --allow-empty -m "Vila das Belezas"
# Giovanni Gronchi Lilac
git commit --allow-empty -m "Giovanni Gronchi"
# Santo Amaro Lilac Emerald
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Santo Amaro" Emerald
git branch -f "Emerald" HEAD
# Largo Treze Lilac
git commit --allow-empty -m "Largo Treze"
# Aldolfo Pinheiro Lilac
git commit --allow-empty -m "Aldolfo Pinheiro"
# Júlio Prestes Diamond
git checkout "Diamond"
git commit --allow-empty -m "Júlio Prestes"
# Faria Lima Yellow
git checkout "Yellow"
git commit --allow-empty -m "Faria Lima"
# Paulista - Consolação Green Yellow
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Paulista - Consolação" Green
git branch -f "Green" HEAD
# Trianon - Masp Green
git checkout "Green"
git commit --allow-empty -m "Trianon - Masp"
# Brigadeiro Green
git commit --allow-empty -m "Brigadeiro"
# República Red Yellow
git checkout "Red"
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "República" Yellow
git branch -f "Yellow" HEAD
# Anhangabaú Red
git commit --allow-empty -m "Anhangabaú"
# Tucuruvi Blue
git checkout --orphan "Blue"
git commit --allow-empty -m "Tucuruvi"
# Parada Inglesa Blue
git commit --allow-empty -m "Parada Inglesa"
# Jardim São Paulo - Ayrton Sena Blue
git commit --allow-empty -m "Jardim São Paulo - Ayrton Sena"
# Carandiru Blue
git commit --allow-empty -m "Carandiru"
# Portuguesa - Tietê Blue
git commit --allow-empty -m "Portuguesa - Tietê"
# Armênia Blue
git commit --allow-empty -m "Armênia"
# Tiradentes Blue
git commit --allow-empty -m "Tiradentes"
# Luz Blue Yellow Ruby Coral
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Luz" Yellow Ruby
git branch -f "Yellow" HEAD
git branch -f "Ruby" HEAD
git branch -f "Coral" HEAD
# São Bento Blue
git commit --allow-empty -m "São Bento"
# Sé Blue Red
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Sé" Red
git branch -f "Red" HEAD
# Liberdade Blue
git commit --allow-empty -m "Liberdade"
# São Joaquim Blue
git commit --allow-empty -m "São Joaquim"
# Vergueiro Blue
git commit --allow-empty -m "Vergueiro"
# Paraíso Blue Green
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Paraíso" Green
git branch -f "Green" HEAD
# Ana Rosa Blue Green
git commit --allow-empty -m "Ana Rosa"
git branch -f "Green" HEAD
# Chácara Klabin Green
git checkout "Green"
git commit --allow-empty -m "Chácara Klabin"
# Santos - Imigrantes Green
git commit --allow-empty -m "Santos - Imigrantes"
# Alto do Ipiranga Green
git commit --allow-empty -m "Alto do Ipiranga"
# Pedro II Red
git checkout "Red"
git commit --allow-empty -m "Pedro II"
# Brás Red Turquoise Coral Sapphire
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Brás" Coral
git branch -f "Coral" HEAD
git branch -f "Turquoise" HEAD
git branch -f "Sapphire" HEAD
# Mooca Turquoise
git checkout "Turquoise"
git commit --allow-empty -m "Mooca"
# Ipiranga Turquoise
git commit --allow-empty -m "Ipiranga"
# Tamanduateí Green Turquoise
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Tamanduateí" Green
git branch -f "Green" HEAD
# Vila Prudente Green Silver
git checkout "Green"
git commit --allow-empty -m "Vila Prudente"
git branch -f "Silver" HEAD
# Oratório Silver
git checkout "Silver"
git commit --allow-empty -m "Oratório"
# Socorro Emerald
git checkout "Emerald"
git commit --allow-empty -m "Socorro"
# Jurubatuba Emerald
git commit --allow-empty -m "Jurubatuba"
# Autódromo Emerald
git commit --allow-empty -m "Autódromo"
# Primavera - Interlagos Emerald
git commit --allow-empty -m "Primavera - Interlagos"
# Grajaú Emerald
git commit --allow-empty -m "Grajaú"
# Vila Mariana Blue
git checkout "Blue"
git commit --allow-empty -m "Vila Mariana"
# Santa Cruz Blue
git commit --allow-empty -m "Santa Cruz"
# Praça da Árvore Blue
git commit --allow-empty -m "Praça da Árvore"
# Saúde Blue
git commit --allow-empty -m "Saúde"
# São Judas Blue
git commit --allow-empty -m "São Judas"
# Conceição Blue
git commit --allow-empty -m "Conceição"
# Jabaquara Blue
git commit --allow-empty -m "Jabaquara"
# Bresser - Mooca Red
git checkout "Red"
git commit --allow-empty -m "Bresser - Mooca"
# Belém Red
git commit --allow-empty -m "Belém"
# Tatuapé Red Coral Sapphire
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Tatuapé" Coral Sapphire
git branch -f "Coral" HEAD
git branch -f "Sapphire" HEAD
# Eng Goulart Sapphire
git checkout "Sapphire"
git commit --allow-empty -m "Eng Goulart"
# USP Leste Sapphire
git commit --allow-empty -m "USP Leste"
# Comendador Ermelino Sapphire
git commit --allow-empty -m "Comendador Ermelino"
# São Miguel Paulista Sapphire
git commit --allow-empty -m "São Miguel Paulista"
# Jardim Helena - Vila Mara Sapphire
git commit --allow-empty -m "Jardim Helena - Vila Mara"
# Jardim Romano Sapphire
git commit --allow-empty -m "Jardim Romano"
# Eng Manoel Feio Sapphire
git commit --allow-empty -m "Eng Manoel Feio"
# Itaquaquecetuba Sapphire
git commit --allow-empty -m "Itaquaquecetuba"
# Aracaré Sapphire
git commit --allow-empty -m "Aracaré"
# Carrão Red
git checkout "Red"
git commit --allow-empty -m "Carrão"
# Penha Red
git commit --allow-empty -m "Penha"
# Vila Matilde Red
git commit --allow-empty -m "Vila Matilde"
# Guilhermina - Esperança Red
git commit --allow-empty -m "Guilhermina - Esperança"
# Patriarca Red
git commit --allow-empty -m "Patriarca"
# Artur Alvim Red
git commit --allow-empty -m "Artur Alvim"
# Corinthians - Itaquera Red Coral
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Corinthians - Itaquera" Coral
git branch -f "Coral" HEAD
# Dom Bosco Coral
git checkout "Coral"
git commit --allow-empty -m "Dom Bosco"
# José Bonifácio Coral
git commit --allow-empty -m "José Bonifácio"
# Guaianazes Coral
git commit --allow-empty -m "Guaianazes"
# Antonio Gianetti Neto Coral
git commit --allow-empty -m "Antonio Gianetti Neto"
# Ferraz de Vasconcelos Coral
git commit --allow-empty -m "Ferraz de Vasconcelos"
# Poá Coral
git commit --allow-empty -m "Poá"
# Calmon Viana Coral Sapphire
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Calmon Viana" Sapphire
git branch -f "Sapphire" HEAD
# Suzano Coral
git commit --allow-empty -m "Suzano"
# Junidapeba Coral
git commit --allow-empty -m "Junidapeba"
# Braz Cubas Coral
git commit --allow-empty -m "Braz Cubas"
# Mogi das Cruzes Coral
git commit --allow-empty -m "Mogi das Cruzes"
# Estudantes Coral
git commit --allow-empty -m "Estudantes"
# São Caetano Turquoise
git checkout "Turquoise"
git commit --allow-empty -m "São Caetano"
# Utinga Turquoise
git commit --allow-empty -m "Utinga"
# Prefeito Saladino Turquoise
git commit --allow-empty -m "Prefeito Saladino"
# Santo André Turquoise
git commit --allow-empty -m "Santo André"
# Capuava Turquoise
git commit --allow-empty -m "Capuava"
# Mauá Turquoise
git commit --allow-empty -m "Mauá"
# Guapituba Turquoise
git commit --allow-empty -m "Guapituba"
# Ribeirão Pires Turquoise
git commit --allow-empty -m "Ribeirão Pires"
# Rio Grande da Serra Turquoise
git commit --allow-empty -m "Rio Grande da Serra"