# Washington Heights - 168 Street / Broadway C
git checkout --orphan "C"
git commit --allow-empty -m "Washington Heights - 168 Street / Broadway"
# 163 Street - Amsterdam Avenue Saint Nicholas Avenue C
git commit --allow-empty -m "163 Street - Amsterdam Avenue Saint Nicholas Avenue"
# Inwood - 207 Street / Broadway A
git checkout --orphan "A"
git commit --allow-empty -m "Inwood - 207 Street / Broadway"
# Dyckman Street / Broadway A
git commit --allow-empty -m "Dyckman Street / Broadway"
# 190 Street / Fort Washington Avenue A
git commit --allow-empty -m "190 Street / Fort Washington Avenue"
# 181 Street / Fort Washington Avenue A
git commit --allow-empty -m "181 Street / Fort Washington Avenue"
# 175 Street / Fort Washington Avenue A
git commit --allow-empty -m "175 Street / Fort Washington Avenue"
# Washington Hts - 168 Street / Broadway A
git commit --allow-empty -m "Washington Hts - 168 Street / Broadway"
# 163 Street - Amsterdam Avenue / Saint Nicholas Avenue A
git commit --allow-empty -m "163 Street - Amsterdam Avenue / Saint Nicholas Avenue"
# 155 Street / Saint Nicholas Avenue A C
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "155 Street / Saint Nicholas Avenue" C
git branch -f "C" HEAD
# Woodlawn / Jerome Avenue 4
git checkout --orphan "4"
git commit --allow-empty -m "Woodlawn / Jerome Avenue"
# Mosholu Parkway / Jerome Avenue 4
git commit --allow-empty -m "Mosholu Parkway / Jerome Avenue"
# Bedford Park Boulevard - Lehman College / Jerome Avenue 4
git commit --allow-empty -m "Bedford Park Boulevard - Lehman College / Jerome Avenue"
# Kingsbridge Road / Jerome Avenue 4
git commit --allow-empty -m "Kingsbridge Road / Jerome Avenue"
# Fordham Road / Jerome Avenue 4
git commit --allow-empty -m "Fordham Road / Jerome Avenue"
# 183 Street / Jerome Avenue 4
git commit --allow-empty -m "183 Street / Jerome Avenue"
# Burnside Avenue / Jerome Avenue 4
git commit --allow-empty -m "Burnside Avenue / Jerome Avenue"
# 176 Street / Jerome Avenue 4
git commit --allow-empty -m "176 Street / Jerome Avenue"
# Mt Eden Avenue / Jerome Avenue 4
git commit --allow-empty -m "Mt Eden Avenue / Jerome Avenue"
# 170 Street / Jerome Avenue 4
git commit --allow-empty -m "170 Street / Jerome Avenue"
# 167 Street / River Avenue 4
git commit --allow-empty -m "167 Street / River Avenue"
# Bedford Park Boulevard / Grand Concourse B D
git checkout --orphan "B"
git commit --allow-empty -m "Bedford Park Boulevard / Grand Concourse"
git branch -f "D" HEAD
# Kingsbridge Road / Grand Concourse B D
git commit --allow-empty -m "Kingsbridge Road / Grand Concourse"
git branch -f "D" HEAD
# Fordham Road / Grand Concourse B D
git commit --allow-empty -m "Fordham Road / Grand Concourse"
git branch -f "D" HEAD
# 182 - 183 Streets / Grand Concourse B D
git commit --allow-empty -m "182 - 183 Streets / Grand Concourse"
git branch -f "D" HEAD
# Tremont Avenue / Grand Concourse B D
git commit --allow-empty -m "Tremont Avenue / Grand Concourse"
git branch -f "D" HEAD
# 174 - 175 Streets / Grand Concourse B D
git commit --allow-empty -m "174 - 175 Streets / Grand Concourse"
git branch -f "D" HEAD
# 170 Street / Grand Concourse B D
git commit --allow-empty -m "170 Street / Grand Concourse"
git branch -f "D" HEAD
# 167 Street / Grand Concourse B D
git commit --allow-empty -m "167 Street / Grand Concourse"
git branch -f "D" HEAD
# 161 Street - Yankee Stadium / River Avenue 4 B D
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "161 Street - Yankee Stadium / River Avenue" 4
git branch -f "4" HEAD
git branch -f "D" HEAD
# 155 Street / 8 Avenue B D
git commit --allow-empty -m "155 Street / 8 Avenue"
git branch -f "D" HEAD
# 145 Street / Saint Nicholas Avenue A B C D
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "145 Street / Saint Nicholas Avenue" A C
git branch -f "A" HEAD
git branch -f "C" HEAD
git branch -f "D" HEAD
# 135 Street Saint Nicholas Avenue A B C
git commit --allow-empty -m "135 Street Saint Nicholas Avenue"
git branch -f "C" HEAD
git branch -f "A" HEAD
# 125 Street / Saint Nicholas Avenue A B C D
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "125 Street / Saint Nicholas Avenue" D
git branch -f "D" HEAD
git branch -f "C" HEAD
git branch -f "A" HEAD
# 116 Street / 8 Avenue A B C
git commit --allow-empty -m "116 Street / 8 Avenue"
git branch -f "C" HEAD
git branch -f "A" HEAD
# Cathedral Parkway (110 Street) / Central Park West A B C
git commit --allow-empty -m "Cathedral Parkway (110 Street) / Central Park West"
git branch -f "C" HEAD
git branch -f "A" HEAD
# 103 Street / Central Park West A B C
git commit --allow-empty -m "103 Street / Central Park West"
git branch -f "C" HEAD
git branch -f "A" HEAD
# 96 Street / Central Park West A B C
git commit --allow-empty -m "96 Street / Central Park West"
git branch -f "C" HEAD
git branch -f "A" HEAD
# 86 Street / Central Park West A B C
git commit --allow-empty -m "86 Street / Central Park West"
git branch -f "C" HEAD
git branch -f "A" HEAD
# 81 Street - Museum of Natural History / Central Park West A B C
git commit --allow-empty -m "81 Street - Museum of Natural History / Central Park West"
git branch -f "C" HEAD
git branch -f "A" HEAD
# 72 Street / Central Park West A B C
git commit --allow-empty -m "72 Street / Central Park West"
git branch -f "C" HEAD
git branch -f "A" HEAD
# 59 Street - Columbus Circle / 8 Avenue A B C D
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "59 Street - Columbus Circle / 8 Avenue" D
git branch -f "D" HEAD
git branch -f "C" HEAD
git branch -f "A" HEAD
# 50 Street / 8 Avenue (Southbound only) A
git checkout "A"
git commit --allow-empty -m "50 Street / 8 Avenue (Southbound only)"
# Flushing - Main Street / Roosevelt Avenue 7
git checkout --orphan "7"
git commit --allow-empty -m "Flushing - Main Street / Roosevelt Avenue"
# Mets - Willets Point 7
git commit --allow-empty -m "Mets - Willets Point"
# 111 Street / Roosevelt Avenue 7
git commit --allow-empty -m "111 Street / Roosevelt Avenue"
# 103 Street - Corona Plaza / Roosevelt Avenue 7
git commit --allow-empty -m "103 Street - Corona Plaza / Roosevelt Avenue"
# Junction Boulevard / Roosevelt Avenue 7
git commit --allow-empty -m "Junction Boulevard / Roosevelt Avenue"
# 90 Street - Elmhurst Avenue / Roosevelt Avenue 7
git commit --allow-empty -m "90 Street - Elmhurst Avenue / Roosevelt Avenue"
# 82 Street - Jackson Heights / Roosevelt Avenue 7
git commit --allow-empty -m "82 Street - Jackson Heights / Roosevelt Avenue"
# 74 Street - Broadway / Roosevelt Av 7
git commit --allow-empty -m "74 Street - Broadway / Roosevelt Av"
# 69 Street / Roosevelt Avenue 7
git commit --allow-empty -m "69 Street / Roosevelt Avenue"
# Woodside - 61Street / Roosevelt Avenue 7
git commit --allow-empty -m "Woodside - 61Street / Roosevelt Avenue"
# 52 Street / Roosevelt Avenue 7
git commit --allow-empty -m "52 Street / Roosevelt Avenue"
# 46 Street / Queens Boulevard 7
git commit --allow-empty -m "46 Street / Queens Boulevard"
# 40 Street / Queens Boulevard 7
git commit --allow-empty -m "40 Street / Queens Boulevard"
# 33 Street / Queens Boulevard 7
git commit --allow-empty -m "33 Street / Queens Boulevard"
# Queensboro Plaza / Queensboro Bridge 7
git commit --allow-empty -m "Queensboro Plaza / Queensboro Bridge"
# Jamaica Center - Parsons / Archer E J Z
git checkout --orphan "E"
git commit --allow-empty -m "Jamaica Center - Parsons / Archer"
git branch -f "J" HEAD
git branch -f "Z" HEAD
# Sutphin Boulevard / Archer Avenue - JFK Airport E J Z
git commit --allow-empty -m "Sutphin Boulevard / Archer Avenue - JFK Airport"
git branch -f "J" HEAD
git branch -f "Z" HEAD
# 121 Street / Jamaica Avenue J Z
git checkout "J"
git commit --allow-empty -m "121 Street / Jamaica Avenue"
git branch -f "Z" HEAD
# 111 Street / Jamaica Avenue J
git commit --allow-empty -m "111 Street / Jamaica Avenue"
# 104 Street / Jamaica Avenue J Z
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "104 Street / Jamaica Avenue" Z
git branch -f "Z" HEAD
# Woodhaven Boulevard / Jamaica Avenue J Z
git commit --allow-empty -m "Woodhaven Boulevard / Jamaica Avenue"
git branch -f "Z" HEAD
# 85 Street - Forest Parkway / Jamaica Avenue J
git commit --allow-empty -m "85 Street - Forest Parkway / Jamaica Avenue"
# 75 Street / Jamaica Avenue J Z
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "75 Street / Jamaica Avenue" Z
git branch -f "Z" HEAD
# Cypress Hills / Jamaica Avenue J
git commit --allow-empty -m "Cypress Hills / Jamaica Avenue"
# Crescent Street / Fulton Street J Z
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Crescent Street / Fulton Street" Z
git branch -f "Z" HEAD
# Norwood Avenue / Fulton Street J Z
git commit --allow-empty -m "Norwood Avenue / Fulton Street"
git branch -f "Z" HEAD
# Cleveland Street / Fulton Street J
git commit --allow-empty -m "Cleveland Street / Fulton Street"
# Van Siclen Avenue / Fulton Street J Z
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Van Siclen Avenue / Fulton Street" Z
git branch -f "Z" HEAD
# Alabama Avenue / Fulton Street J Z
git commit --allow-empty -m "Alabama Avenue / Fulton Street"
git branch -f "Z" HEAD
# Jamaica - Van Wyck / Queens Boulevard E
git checkout "E"
git commit --allow-empty -m "Jamaica - Van Wyck / Queens Boulevard"
# Jamaica - 179 Street / Hillside Avenue F
git checkout --orphan "F"
git commit --allow-empty -m "Jamaica - 179 Street / Hillside Avenue"
# 169 Street / Hillside Avenue F
git commit --allow-empty -m "169 Street / Hillside Avenue"
# Parsons Boulevard / Hillside Avenue F
git commit --allow-empty -m "Parsons Boulevard / Hillside Avenue"
# Sutphin Boulevard / Hillside Avenue F
git commit --allow-empty -m "Sutphin Boulevard / Hillside Avenue"
# Briarwood / Main Street E F
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Briarwood / Main Street" E
git branch -f "E" HEAD
# Kew Gardens Union Turnpike / Queens Boulevard F
git commit --allow-empty -m "Kew Gardens Union Turnpike / Queens Boulevard"
# Kew Gardens - Union Turnpike / Queens Boulevard E
git checkout "E"
git commit --allow-empty -m "Kew Gardens - Union Turnpike / Queens Boulevard"
# 75 Avenue / Queens Boulevard E F
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "75 Avenue / Queens Boulevard" F
git branch -f "F" HEAD
# Forest Hills - 71 Avenue / Queens Boulevard E F M R
git commit --allow-empty -m "Forest Hills - 71 Avenue / Queens Boulevard"
git branch -f "M" HEAD
git branch -f "R" HEAD
git branch -f "F" HEAD
# 67 Avenue / Queens Boulevard E M R
git commit --allow-empty -m "67 Avenue / Queens Boulevard"
git branch -f "M" HEAD
git branch -f "R" HEAD
# 63 Drive - Rego Park / Queens Boulevard E M R
git commit --allow-empty -m "63 Drive - Rego Park / Queens Boulevard"
git branch -f "M" HEAD
git branch -f "R" HEAD
# Woodhaven Boulevard / Queens Boulevard E M R
git commit --allow-empty -m "Woodhaven Boulevard / Queens Boulevard"
git branch -f "M" HEAD
git branch -f "R" HEAD
# Grand Avenue - Newtown / Queens Boulevard E M R
git commit --allow-empty -m "Grand Avenue - Newtown / Queens Boulevard"
git branch -f "M" HEAD
git branch -f "R" HEAD
# Elmhurst Avenue / Broadway E M R
git commit --allow-empty -m "Elmhurst Avenue / Broadway"
git branch -f "M" HEAD
git branch -f "R" HEAD
# Jackson Heights - Roosevelt Avenue / Broadway E F M
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Jackson Heights - Roosevelt Avenue / Broadway" F
git branch -f "F" HEAD
git branch -f "M" HEAD
# 21 Street - Queensbridge / 41 Avenue F
git checkout "F"
git commit --allow-empty -m "21 Street - Queensbridge / 41 Avenue"
# Roosevelt Island F
git commit --allow-empty -m "Roosevelt Island"
# Lexington Av / 63 Street F
git commit --allow-empty -m "Lexington Av / 63 Street"
# 57 Street / 6 Avenue F
git commit --allow-empty -m "57 Street / 6 Avenue"
# Jackson Heights / Roosevelt Avenue / Broadway R
git checkout "R"
git commit --allow-empty -m "Jackson Heights / Roosevelt Avenue / Broadway"
# 65 Street / Broadway E M R
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "65 Street / Broadway" E M
git branch -f "E" HEAD
git branch -f "M" HEAD
# Northern Boulevard / Broadway E M R
git commit --allow-empty -m "Northern Boulevard / Broadway"
git branch -f "M" HEAD
git branch -f "E" HEAD
# 46 Street / Broadway E M R
git commit --allow-empty -m "46 Street / Broadway"
git branch -f "M" HEAD
git branch -f "E" HEAD
# Steinway Street / Broadway E M R
git commit --allow-empty -m "Steinway Street / Broadway"
git branch -f "M" HEAD
git branch -f "E" HEAD
# 36 Street / Northern Boulevard E M R
git commit --allow-empty -m "36 Street / Northern Boulevard"
git branch -f "M" HEAD
git branch -f "E" HEAD
# Queens Plaza / Queens Boulevard E M R
git commit --allow-empty -m "Queens Plaza / Queens Boulevard"
git branch -f "M" HEAD
git branch -f "E" HEAD
# Astoria - Ditmars Boulevard / 31 Street N W
git checkout --orphan "N"
git commit --allow-empty -m "Astoria - Ditmars Boulevard / 31 Street"
git branch -f "W" HEAD
# Astoria Boulevard / 31 Street N W
git commit --allow-empty -m "Astoria Boulevard / 31 Street"
git branch -f "W" HEAD
# 30 Avenue / 31 Street N W
git commit --allow-empty -m "30 Avenue / 31 Street"
git branch -f "W" HEAD
# Broadway / 31 Street N W
git commit --allow-empty -m "Broadway / 31 Street"
git branch -f "W" HEAD
# 36 Avenue / 31 Street N W
git commit --allow-empty -m "36 Avenue / 31 Street"
git branch -f "W" HEAD
# 39 Avenue / 31 Street N W
git commit --allow-empty -m "39 Avenue / 31 Street"
git branch -f "W" HEAD
# Queensboro Plaza / Bridge Plaza N W
git commit --allow-empty -m "Queensboro Plaza / Bridge Plaza"
git branch -f "W" HEAD
# Lexington Avenue / 59 Street N R W
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Lexington Avenue / 59 Street" R
git branch -f "R" HEAD
git branch -f "W" HEAD
# 5 Avenue / 59 Street N R W
git commit --allow-empty -m "5 Avenue / 59 Street"
git branch -f "R" HEAD
git branch -f "W" HEAD
# 96 Street / 2 Avenue Q
git checkout --orphan "Q"
git commit --allow-empty -m "96 Street / 2 Avenue"
# 86 Street / 2 Avenue Q
git commit --allow-empty -m "86 Street / 2 Avenue"
# 72 Street / 2 Avenue Q
git commit --allow-empty -m "72 Street / 2 Avenue"
# 57 Street / 7 Avenue N Q R W
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "57 Street / 7 Avenue" N R W
git branch -f "N" HEAD
git branch -f "R" HEAD
git branch -f "W" HEAD
# 49 Street / 7 Avenue (Northbound Only) W
git checkout "W"
git commit --allow-empty -m "49 Street / 7 Avenue (Northbound Only)"
# 42 Street - Times Square / Broadway except W
git commit --allow-empty -m "42 Street - Times Square / Broadway except"
# Van Cortlandt Park - 242 Street / Broadway 1
git checkout --orphan "1"
git commit --allow-empty -m "Van Cortlandt Park - 242 Street / Broadway"
# 238 Street / Broadway 1
git commit --allow-empty -m "238 Street / Broadway"
# 231 Street / Broadway 1
git commit --allow-empty -m "231 Street / Broadway"
# Marble Hill - 225 Street / Broadway 1
git commit --allow-empty -m "Marble Hill - 225 Street / Broadway"
# 215 Street / 10 Avenue 1
git commit --allow-empty -m "215 Street / 10 Avenue"
# 207 Street / 10 Avenue 1
git commit --allow-empty -m "207 Street / 10 Avenue"
# Dyckman Street / Nagle Avenue (southbound  only) 1
git commit --allow-empty -m "Dyckman Street / Nagle Avenue (southbound  only)"
# 191 Street / Saint Nicholas Avenue 1
git commit --allow-empty -m "191 Street / Saint Nicholas Avenue"
# 181 Street / Saint Nicholas Avenue 1
git commit --allow-empty -m "181 Street / Saint Nicholas Avenue"
# 168 Street - Washington Heights / Broadway 1
git commit --allow-empty -m "168 Street - Washington Heights / Broadway"
# 157 Street / Broadway 1
git commit --allow-empty -m "157 Street / Broadway"
# 145 Street / Broadway 1
git commit --allow-empty -m "145 Street / Broadway"
# 137 Street - City College / Broadway 1
git commit --allow-empty -m "137 Street - City College / Broadway"
# 125 Street / Broadway 1
git commit --allow-empty -m "125 Street / Broadway"
# 116 Street - Columbia University / Broadway 1
git commit --allow-empty -m "116 Street - Columbia University / Broadway"
# Cathedral Parkway (110 Street) / Broadway 1
git commit --allow-empty -m "Cathedral Parkway (110 Street) / Broadway"
# 103 Street / Broadway 1
git commit --allow-empty -m "103 Street / Broadway"
# Wakefield - 241 Street / White Plains Road 2
git checkout --orphan "2"
git commit --allow-empty -m "Wakefield - 241 Street / White Plains Road"
# Nereid Avenue White Plains Road 2
git commit --allow-empty -m "Nereid Avenue White Plains Road"
# 233 Street / White Plains Road 2
git commit --allow-empty -m "233 Street / White Plains Road"
# 225 Street / White Plains Road 2
git commit --allow-empty -m "225 Street / White Plains Road"
# 219 Street / White Plains Road 2
git commit --allow-empty -m "219 Street / White Plains Road"
# Gun Hill Road / White Plains Road 2
git commit --allow-empty -m "Gun Hill Road / White Plains Road"
# Burke Avenue / White Plains Road 2
git commit --allow-empty -m "Burke Avenue / White Plains Road"
# Allerton Avenue / White Plains Road 2
git commit --allow-empty -m "Allerton Avenue / White Plains Road"
# Pelham Parkway / White Plains Road 2
git commit --allow-empty -m "Pelham Parkway / White Plains Road"
# Bronx Park East / White Plains Road 2
git commit --allow-empty -m "Bronx Park East / White Plains Road"
# East 180 Street / Morris Park Avenue 2 5
git commit --allow-empty -m "East 180 Street / Morris Park Avenue"
git branch -f "5" HEAD
# West Farms Square - East Tremont Avenue / Boston Road 2
git commit --allow-empty -m "West Farms Square - East Tremont Avenue / Boston Road"
# West Farms Square - E Tremont Avenue / Boston Road - Bronx Zoo 5
git checkout "5"
git commit --allow-empty -m "West Farms Square - E Tremont Avenue / Boston Road - Bronx Zoo"
# 174 Street / Southern Boulevard 2 5
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "174 Street / Southern Boulevard" 2
git branch -f "2" HEAD
# Freeman Street / Southern Boulevard 2 5
git commit --allow-empty -m "Freeman Street / Southern Boulevard"
git branch -f "2" HEAD
# Simpson Street / Westchester Avenue 2 5
git commit --allow-empty -m "Simpson Street / Westchester Avenue"
git branch -f "2" HEAD
# Intervale Avenue / Westchester Avenue 2 5
git commit --allow-empty -m "Intervale Avenue / Westchester Avenue"
git branch -f "2" HEAD
# Prospect Avenue / Westchester Avenue 2 5
git commit --allow-empty -m "Prospect Avenue / Westchester Avenue"
git branch -f "2" HEAD
# Jackson Avenue / Westchester Avenue 2 5
git commit --allow-empty -m "Jackson Avenue / Westchester Avenue"
git branch -f "2" HEAD
# 3 Avenue - 149 Street 2 5
git commit --allow-empty -m "3 Avenue - 149 Street"
git branch -f "2" HEAD
# 149 Street - Grand Concourse 2 4 5
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "149 Street - Grand Concourse" 4
git branch -f "4" HEAD
git branch -f "2" HEAD
# Harlem - 148 Street / 7 Avenue 3
git checkout --orphan "3"
git commit --allow-empty -m "Harlem - 148 Street / 7 Avenue"
# 145 Street / Lenox Avenue 3
git commit --allow-empty -m "145 Street / Lenox Avenue"
# 135 Street / Lenox Avenue 2 3
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "135 Street / Lenox Avenue" 2
git branch -f "2" HEAD
# 125 Street / Lenox Avenue 2 3
git commit --allow-empty -m "125 Street / Lenox Avenue"
git branch -f "2" HEAD
# 116 Street / Lenox Avenue 2 3
git commit --allow-empty -m "116 Street / Lenox Avenue"
git branch -f "2" HEAD
# Central Park North (110 Street) / Lenox Avenue 2 3
git commit --allow-empty -m "Central Park North (110 Street) / Lenox Avenue"
git branch -f "2" HEAD
# 96 Street / Broadway 1 2 3
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "96 Street / Broadway" 1
git branch -f "1" HEAD
git branch -f "2" HEAD
# 86 Street / Broadway 1 2
git checkout "1"
git commit --allow-empty -m "86 Street / Broadway"
git branch -f "2" HEAD
# 79 Street / Broadway 1 2
git commit --allow-empty -m "79 Street / Broadway"
git branch -f "2" HEAD
# 72 Street / Broadway 1 2 3
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "72 Street / Broadway" 3
git branch -f "3" HEAD
git branch -f "2" HEAD
# 66 Street - Lincoln Center / Broadway 1 2
git commit --allow-empty -m "66 Street - Lincoln Center / Broadway"
git branch -f "2" HEAD
# 59 Street - Columbus Circle / Broadway 1 2
git commit --allow-empty -m "59 Street - Columbus Circle / Broadway"
git branch -f "2" HEAD
# 50 Street / Broadway 1 2
git commit --allow-empty -m "50 Street / Broadway"
git branch -f "2" HEAD
# 57 Street / N
git checkout "N"
git commit --allow-empty -m "57 Street /"
# 49 Street / 7 Avenue (North - bound only) N R
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "49 Street / 7 Avenue (North - bound only)" R
git branch -f "R" HEAD
# 49 Street (Northbound) Q
git checkout "Q"
git commit --allow-empty -m "49 Street (Northbound)"
# Times Square - 42 Street / 7 Avenue - Broadway 1 2 3 N Q R
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Times Square - 42 Street / 7 Avenue - Broadway" 1 2 3 N R
git branch -f "1" HEAD
git branch -f "2" HEAD
git branch -f "3" HEAD
git branch -f "N" HEAD
git branch -f "R" HEAD
# 34 Street - Herald Square / Broadway N Q R W
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "34 Street - Herald Square / Broadway" W
git branch -f "W" HEAD
git branch -f "R" HEAD
git branch -f "N" HEAD
# 28 Street / Broadway N Q R W
git commit --allow-empty -m "28 Street / Broadway"
git branch -f "R" HEAD
git branch -f "N" HEAD
git branch -f "W" HEAD
# 23 Street / Broadway N Q R W
git commit --allow-empty -m "23 Street / Broadway"
git branch -f "R" HEAD
git branch -f "N" HEAD
git branch -f "W" HEAD
# 14 Street - Union Square / Broadway N Q R W
git commit --allow-empty -m "14 Street - Union Square / Broadway"
git branch -f "R" HEAD
git branch -f "N" HEAD
git branch -f "W" HEAD
# 8 Street - New York University / Broadway N Q R W
git commit --allow-empty -m "8 Street - New York University / Broadway"
git branch -f "R" HEAD
git branch -f "N" HEAD
git branch -f "W" HEAD
# Prince Street / Broadway N Q R W
git commit --allow-empty -m "Prince Street / Broadway"
git branch -f "R" HEAD
git branch -f "N" HEAD
git branch -f "W" HEAD
# Canal Street / Broadway N Q R W
git commit --allow-empty -m "Canal Street / Broadway"
git branch -f "R" HEAD
git branch -f "N" HEAD
git branch -f "W" HEAD
# City Hall / Broadway N R W
git checkout "N"
git commit --allow-empty -m "City Hall / Broadway"
git branch -f "R" HEAD
git branch -f "W" HEAD
# Cortlandt Street / Church Street R W
git checkout "R"
git commit --allow-empty -m "Cortlandt Street / Church Street"
git branch -f "W" HEAD
# Rector Street / Trinity Place N R W
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Rector Street / Trinity Place" N
git branch -f "N" HEAD
git branch -f "W" HEAD
# Whitehall Street South Ferry / Water Street N R
git commit --allow-empty -m "Whitehall Street South Ferry / Water Street"
git branch -f "N" HEAD
# Court Street - Borough Hall / Montague Street N R
git commit --allow-empty -m "Court Street - Borough Hall / Montague Street"
git branch -f "N" HEAD
# Pelham Bay Park / Bruckner Expressway 6
git checkout --orphan "6"
git commit --allow-empty -m "Pelham Bay Park / Bruckner Expressway"
# Buhre Avenue / Westchester Avenue 6
git commit --allow-empty -m "Buhre Avenue / Westchester Avenue"
# Westchester Square - East Tremont Avenue / Westchester Avenue 6
git commit --allow-empty -m "Westchester Square - East Tremont Avenue / Westchester Avenue"
# Zerega Avenue / Westchester Avenue 6
git commit --allow-empty -m "Zerega Avenue / Westchester Avenue"
# Parkchester / Westchester Avenue 6
git commit --allow-empty -m "Parkchester / Westchester Avenue"
# Saint Lawrence Avenue / Westchester Avenue 6
git commit --allow-empty -m "Saint Lawrence Avenue / Westchester Avenue"
# Morrison - Sound View Avenues / Westchester Avenue 6
git commit --allow-empty -m "Morrison - Sound View Avenues / Westchester Avenue"
# Elder Avenue / Westchester Avenue 6
git commit --allow-empty -m "Elder Avenue / Westchester Avenue"
# Whitlock Avenue / Westchester Avenue 6
git commit --allow-empty -m "Whitlock Avenue / Westchester Avenue"
# Hunts Point Avenue / Southern Boulevard 6
git commit --allow-empty -m "Hunts Point Avenue / Southern Boulevard"
# Longwood Avenue / Southern Boulevard 6
git commit --allow-empty -m "Longwood Avenue / Southern Boulevard"
# East 149 Street / Southern Boulevard 6
git commit --allow-empty -m "East 149 Street / Southern Boulevard"
# East 143 Street - Saint Mary's Street / Southern Boulevard 6
git commit --allow-empty -m "East 143 Street - Saint Mary's Street / Southern Boulevard"
# Cypress Avenue / East 138 Street 6
git commit --allow-empty -m "Cypress Avenue / East 138 Street"
# Brook Avenue / East 138 Street 6
git commit --allow-empty -m "Brook Avenue / East 138 Street"
# 3 Avenue - 138 Street 6
git commit --allow-empty -m "3 Avenue - 138 Street"
# 138 Street / Grand Concourse 4 5
git checkout "4"
git commit --allow-empty -m "138 Street / Grand Concourse"
git branch -f "5" HEAD
# 125 Street / Lexington Avenue 4 5 6
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "125 Street / Lexington Avenue" 6
git branch -f "6" HEAD
git branch -f "5" HEAD
# 116 Street / Lexington Avenue 4 6
git commit --allow-empty -m "116 Street / Lexington Avenue"
git branch -f "6" HEAD
# 110 Street / Lexington Avenue 4 6
git commit --allow-empty -m "110 Street / Lexington Avenue"
git branch -f "6" HEAD
# 103 Street / Lexington Avenue 4 6
git commit --allow-empty -m "103 Street / Lexington Avenue"
git branch -f "6" HEAD
# 96 Street / Lexington Avenue 4 6
git commit --allow-empty -m "96 Street / Lexington Avenue"
git branch -f "6" HEAD
# 86 Street / Lexington Avenue 4 5 6
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "86 Street / Lexington Avenue" 5
git branch -f "5" HEAD
git branch -f "6" HEAD
# 77 Street / Lexington Avenue 4 6
git commit --allow-empty -m "77 Street / Lexington Avenue"
git branch -f "6" HEAD
# 68 Street - Hunter College / Lexington Avenue 4 6
git commit --allow-empty -m "68 Street - Hunter College / Lexington Avenue"
git branch -f "6" HEAD
# 59 Street / Lexington Avenue 4 5 6
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "59 Street / Lexington Avenue" 5
git branch -f "5" HEAD
git branch -f "6" HEAD
# 51 Street / Lexington Avenue 4 6
git commit --allow-empty -m "51 Street / Lexington Avenue"
git branch -f "6" HEAD
# Court Sq 7 E G M
git checkout "7"
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Court Sq" E M
git branch -f "E" HEAD
git branch -f "M" HEAD
git branch -f "G" HEAD
# Lexington Avenue / 53 Street E M
git checkout "E"
git commit --allow-empty -m "Lexington Avenue / 53 Street"
git branch -f "M" HEAD
# 5 Avenue / 53 Street E M
git commit --allow-empty -m "5 Avenue / 53 Street"
git branch -f "M" HEAD
# 7 Avenue / 53 Street B D E
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "7 Avenue / 53 Street" B D
git branch -f "B" HEAD
git branch -f "D" HEAD
# 47 - 50 Streets - Rockefeller Center / 6 Avenue D F M
git checkout "D"
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "47 - 50 Streets - Rockefeller Center / 6 Avenue" F M
git branch -f "F" HEAD
git branch -f "M" HEAD
# 47 - 50 Street - Rockefeller Center / 6 Avenue B
git checkout "B"
git commit --allow-empty -m "47 - 50 Street - Rockefeller Center / 6 Avenue"
# 42 Street - Bryant Park / 6 Avenue B D F M
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "42 Street - Bryant Park / 6 Avenue" D F M
git branch -f "D" HEAD
git branch -f "F" HEAD
git branch -f "M" HEAD
# 34 Street - Herald Square / 6 Avenue B D F M
git commit --allow-empty -m "34 Street - Herald Square / 6 Avenue"
git branch -f "M" HEAD
git branch -f "F" HEAD
git branch -f "D" HEAD
# 23 Street / 6 Avenue F M
git checkout "F"
git commit --allow-empty -m "23 Street / 6 Avenue"
git branch -f "M" HEAD
# 14 Street / 6 Avenue F M
git commit --allow-empty -m "14 Street / 6 Avenue"
git branch -f "M" HEAD
# 50 Street / 8 Avenue (downtown only) E
git checkout "E"
git commit --allow-empty -m "50 Street / 8 Avenue (downtown only)"
# 50 Street / 8 Avenue Downtown only C
git checkout "C"
git commit --allow-empty -m "50 Street / 8 Avenue Downtown only"
# 42 Street - Port Authority Bus Terminal / 8 Avenue A C E
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "42 Street - Port Authority Bus Terminal / 8 Avenue" A E
git branch -f "A" HEAD
git branch -f "E" HEAD
# 34 Street - Penn Station / 8 Avenue A C E
git commit --allow-empty -m "34 Street - Penn Station / 8 Avenue"
git branch -f "E" HEAD
git branch -f "A" HEAD
# 23 Street / 8 Avenue A C E
git commit --allow-empty -m "23 Street / 8 Avenue"
git branch -f "E" HEAD
git branch -f "A" HEAD
# 14 Street / 8 Avenue A C E
git commit --allow-empty -m "14 Street / 8 Avenue"
git branch -f "E" HEAD
git branch -f "A" HEAD
# West 4 Street - Washington Square / 6 Avenue A B C D E F M
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "West 4 Street - Washington Square / 6 Avenue" B D F M
git branch -f "B" HEAD
git branch -f "D" HEAD
git branch -f "F" HEAD
git branch -f "M" HEAD
git branch -f "E" HEAD
git branch -f "A" HEAD
# Hunters Point Avenue / 49 Avenue / 21 Street 7
git checkout "7"
git commit --allow-empty -m "Hunters Point Avenue / 49 Avenue / 21 Street"
# Vernon Boulevard - Jackson Avenue 7
git commit --allow-empty -m "Vernon Boulevard - Jackson Avenue"
# Grand Central - 42 Street / Lexington Avenue 4 5 6 7
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Grand Central - 42 Street / Lexington Avenue" 4 5 6
git branch -f "4" HEAD
git branch -f "5" HEAD
git branch -f "6" HEAD
# 33 Street / Park Avenue South 4 6
git checkout "4"
git commit --allow-empty -m "33 Street / Park Avenue South"
git branch -f "6" HEAD
# 28 Street / Park Avenue South 4 6
git commit --allow-empty -m "28 Street / Park Avenue South"
git branch -f "6" HEAD
# 23 Street / Park Avenue South 4 6
git commit --allow-empty -m "23 Street / Park Avenue South"
git branch -f "6" HEAD
# 14 Street - Union Square / 4 Avenue 4 5 6
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "14 Street - Union Square / 4 Avenue" 5
git branch -f "5" HEAD
git branch -f "6" HEAD
# Astor Place / 4 Avenue 4 6
git commit --allow-empty -m "Astor Place / 4 Avenue"
git branch -f "6" HEAD
# Broadway - Lafayette Street / Bleecker St 4 6 B D F M
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Broadway - Lafayette Street / Bleecker St" B D F M
git branch -f "B" HEAD
git branch -f "D" HEAD
git branch -f "F" HEAD
git branch -f "M" HEAD
git branch -f "6" HEAD
# 21 Street / Jackson Avenue G
git checkout "G"
git commit --allow-empty -m "21 Street / Jackson Avenue"
# Greenpoint Avenue / Manhattan Avenue G
git commit --allow-empty -m "Greenpoint Avenue / Manhattan Avenue"
# Nassau Avenue / Manhattan Avenue G
git commit --allow-empty -m "Nassau Avenue / Manhattan Avenue"
# Metropolitan Avenue / Grand Street G
git commit --allow-empty -m "Metropolitan Avenue / Grand Street"
# Broadway / Union Avenue G
git commit --allow-empty -m "Broadway / Union Avenue"
# Flushing Avenue / Marcy Avenue G
git commit --allow-empty -m "Flushing Avenue / Marcy Avenue"
# Myrtle - Willoughby Avenues / Marcy Avenue G
git commit --allow-empty -m "Myrtle - Willoughby Avenues / Marcy Avenue"
# Bedford - Nostrand Avenues / Lafayette Avenue G
git commit --allow-empty -m "Bedford - Nostrand Avenues / Lafayette Avenue"
# Classon Avenue / Lafayette Avenue G
git commit --allow-empty -m "Classon Avenue / Lafayette Avenue"
# Clinton - Washington Avenues / Lafayette Avenue G
git commit --allow-empty -m "Clinton - Washington Avenues / Lafayette Avenue"
# Fulton Street / Lafayette Avenue G
git commit --allow-empty -m "Fulton Street / Lafayette Avenue"
# Spring Street / 6 Avenue A C E
git checkout "A"
git commit --allow-empty -m "Spring Street / 6 Avenue"
git branch -f "E" HEAD
git branch -f "C" HEAD
# Canal Street / 6 Avenue A C E
git commit --allow-empty -m "Canal Street / 6 Avenue"
git branch -f "E" HEAD
git branch -f "C" HEAD
# Chambers Street / Church Street A C
git commit --allow-empty -m "Chambers Street / Church Street"
git branch -f "C" HEAD
# Spring Street / Lafayette Street 4 6
git checkout "4"
git commit --allow-empty -m "Spring Street / Lafayette Street"
git branch -f "6" HEAD
# Canal Street / Lafayette Street 4 6
git commit --allow-empty -m "Canal Street / Lafayette Street"
git branch -f "6" HEAD
# Brooklyn Bridge - City Hall / Centre Street 4 5 6
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Brooklyn Bridge - City Hall / Centre Street" 5
git branch -f "5" HEAD
git branch -f "6" HEAD
# 34 Street - Penn Station / 7 Avenue 1 2 3
git checkout "1"
git commit --allow-empty -m "34 Street - Penn Station / 7 Avenue"
git branch -f "3" HEAD
git branch -f "2" HEAD
# 28 Street / 7 Avenue 1 2
git commit --allow-empty -m "28 Street / 7 Avenue"
git branch -f "2" HEAD
# 23 Street / 7 Avenue 1 2
git commit --allow-empty -m "23 Street / 7 Avenue"
git branch -f "2" HEAD
# 18 Street / 7 Avenue 1 2
git commit --allow-empty -m "18 Street / 7 Avenue"
git branch -f "2" HEAD
# 14 Street / 7 Avenue 1 2 3
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "14 Street / 7 Avenue" 3
git branch -f "3" HEAD
git branch -f "2" HEAD
# Christopher Street - Sheridan Sq / 7 Avenue 2
git checkout "2"
git commit --allow-empty -m "Christopher Street - Sheridan Sq / 7 Avenue"
# Christopher Street - Sheridan Square / 7 Avenue South 1
git checkout "1"
git commit --allow-empty -m "Christopher Street - Sheridan Square / 7 Avenue South"
# Houston Street / Varick Street 1 2
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Houston Street / Varick Street" 2
git branch -f "2" HEAD
# Canal Street / Varick Street 1 2
git commit --allow-empty -m "Canal Street / Varick Street"
git branch -f "2" HEAD
# Franklin Street / Varick Street 1 2
git commit --allow-empty -m "Franklin Street / Varick Street"
git branch -f "2" HEAD
# Chambers Street / West Broadway 1 2 3
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Chambers Street / West Broadway" 3
git branch -f "3" HEAD
git branch -f "2" HEAD
# Park Place / Broadway 2 3
git checkout "2"
git commit --allow-empty -m "Park Place / Broadway"
git branch -f "3" HEAD
# Fulton Street / Broadway 2 3 4 5 A C
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Fulton Street / Broadway" 4 5 A C
git branch -f "4" HEAD
git branch -f "5" HEAD
git branch -f "A" HEAD
git branch -f "C" HEAD
git branch -f "3" HEAD
# Wall Street / Broadway 4 5
git checkout "4"
git commit --allow-empty -m "Wall Street / Broadway"
git branch -f "5" HEAD
# Bowling Green / Broadway 4 5
git commit --allow-empty -m "Bowling Green / Broadway"
git branch -f "5" HEAD
# Borough Hall / Court Street &   (Northbound only) 5
git checkout "5"
git commit --allow-empty -m "Borough Hall / Court Street &   (Northbound only)"
# Borough Hall - Court Street / Joralemon Street &  (Northbound only) 4
git checkout "4"
git commit --allow-empty -m "Borough Hall - Court Street / Joralemon Street &  (Northbound only)"
# Wall Street / William Street 2 3
git checkout "2"
git commit --allow-empty -m "Wall Street / William Street"
git branch -f "3" HEAD
# Clark Street / Henry Street 2 3
git commit --allow-empty -m "Clark Street / Henry Street"
git branch -f "3" HEAD
# Borough Hall / Court Street - Montague Street &   N / B only 2
git commit --allow-empty -m "Borough Hall / Court Street - Montague Street &   N / B only"
# Borough Hall / Court Street - Montague Street &   (Northbound Only) 3
git checkout "3"
git commit --allow-empty -m "Borough Hall / Court Street - Montague Street &   (Northbound Only)"
# Hoyt Street - Fulton Mall 2 3
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Hoyt Street - Fulton Mall" 2
git branch -f "2" HEAD
# Nevins Street / Flatbush Avenue 2 3 4 5
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Nevins Street / Flatbush Avenue" 4 5
git branch -f "4" HEAD
git branch -f "5" HEAD
git branch -f "2" HEAD
# Grand Street / Chrystie Street B D
git checkout "B"
git commit --allow-empty -m "Grand Street / Chrystie Street"
git branch -f "D" HEAD
# DeKalb Avenue / Flatbush Avenue B D N Q R
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "DeKalb Avenue / Flatbush Avenue" N Q R
git branch -f "N" HEAD
git branch -f "Q" HEAD
git branch -f "R" HEAD
git branch -f "D" HEAD
# Atlantic Avenue / Barclays Center 2 3 4 5 B D Q
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Atlantic Avenue / Barclays Center" 2 3 4 5
git branch -f "2" HEAD
git branch -f "3" HEAD
git branch -f "4" HEAD
git branch -f "5" HEAD
git branch -f "Q" HEAD
git branch -f "D" HEAD
# Bergen Street / Flatbush Avenue 2 3 4
git checkout "2"
git commit --allow-empty -m "Bergen Street / Flatbush Avenue"
git branch -f "3" HEAD
git branch -f "4" HEAD
# Grand Army Plaza / Flatbush Avenue 2 3 4
git commit --allow-empty -m "Grand Army Plaza / Flatbush Avenue"
git branch -f "3" HEAD
git branch -f "4" HEAD
# Eastern Parkway - Brooklyn Museum 2 3 4
git commit --allow-empty -m "Eastern Parkway - Brooklyn Museum"
git branch -f "3" HEAD
git branch -f "4" HEAD
# Franklin Avenue / Eastern Parkway 2 3 4 5
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Franklin Avenue / Eastern Parkway" 5
git branch -f "5" HEAD
git branch -f "3" HEAD
git branch -f "4" HEAD
# Nostrand Avenue / Eastern Parkway 3 4
git checkout "3"
git commit --allow-empty -m "Nostrand Avenue / Eastern Parkway"
git branch -f "4" HEAD
# Kingston Avenue / Eastern Parkway 3 4
git commit --allow-empty -m "Kingston Avenue / Eastern Parkway"
git branch -f "4" HEAD
# Crown Heights - Utica Avenue / Eastern Parkway 3 4
git commit --allow-empty -m "Crown Heights - Utica Avenue / Eastern Parkway"
git branch -f "4" HEAD
# Sutter Avenue - Rutland Road / East 98 Street 3 4
git commit --allow-empty -m "Sutter Avenue - Rutland Road / East 98 Street"
git branch -f "4" HEAD
# Saratoga Avenue / Livonia Avenue 3 4
git commit --allow-empty -m "Saratoga Avenue / Livonia Avenue"
git branch -f "4" HEAD
# Rockaway Avenue / Livonia Avenue 3 4
git commit --allow-empty -m "Rockaway Avenue / Livonia Avenue"
git branch -f "4" HEAD
# Junius Street / Livonia Avenue 3 4
git commit --allow-empty -m "Junius Street / Livonia Avenue"
git branch -f "4" HEAD
# Pennsylvania Avenue / Livonia Avenue 3 4
git commit --allow-empty -m "Pennsylvania Avenue / Livonia Avenue"
git branch -f "4" HEAD
# Van Siclen Avenue / Livonia Avenue 3 4
git commit --allow-empty -m "Van Siclen Avenue / Livonia Avenue"
git branch -f "4" HEAD
# New Lots Avenue / Livonia Avenue 3 4
git commit --allow-empty -m "New Lots Avenue / Livonia Avenue"
git branch -f "4" HEAD
# Whitehall Street Broadway W
git checkout "W"
git commit --allow-empty -m "Whitehall Street Broadway"
# 5 Avenue - Bryant Park / 42 Street 7
git checkout "7"
git commit --allow-empty -m "5 Avenue - Bryant Park / 42 Street"
# Times Square - 42 Street / 7 Avenue - Broadway / 7 Avenue 7
git commit --allow-empty -m "Times Square - 42 Street / 7 Avenue - Broadway / 7 Avenue"
# 2 Avenue / Houston Street F
git checkout "F"
git commit --allow-empty -m "2 Avenue / Houston Street"
# Delancey Street / Essex Street F
git commit --allow-empty -m "Delancey Street / Essex Street"
# East Broadway / Canal Street - Rutgers Street F
git commit --allow-empty -m "East Broadway / Canal Street - Rutgers Street"
# York Street / Jay Street F
git commit --allow-empty -m "York Street / Jay Street"
# High Street / Cadman Plaza East A C
git checkout "A"
git commit --allow-empty -m "High Street / Cadman Plaza East"
git branch -f "C" HEAD
# Jay Street - MetroTech / Fulton Mall A C F
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Jay Street - MetroTech / Fulton Mall" F
git branch -f "F" HEAD
git branch -f "C" HEAD
# Hoyt Street - Schermerhorn Street A C G
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Hoyt Street - Schermerhorn Street" G
git branch -f "G" HEAD
git branch -f "C" HEAD
# Bergen Street / Smith Street F G
git checkout "F"
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Bergen Street / Smith Street" G
git branch -f "G" HEAD
# Carroll Street / Smith Street F G
git commit --allow-empty -m "Carroll Street / Smith Street"
git branch -f "G" HEAD
# Smith - 9 Streets F G
git commit --allow-empty -m "Smith - 9 Streets"
git branch -f "G" HEAD
# Union Street / 4 Avenue D N R
git checkout "D"
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Union Street / 4 Avenue" N R
git branch -f "N" HEAD
git branch -f "R" HEAD
# 4 Avenue / 9 Street D F G N R
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "4 Avenue / 9 Street" F G
git branch -f "F" HEAD
git branch -f "G" HEAD
git branch -f "R" HEAD
git branch -f "N" HEAD
# 7 Avenue / 9 Street F G
git checkout "F"
git commit --allow-empty -m "7 Avenue / 9 Street"
git branch -f "G" HEAD
# 15 Street - Prospect Park / Prospect Park West F G
git commit --allow-empty -m "15 Street - Prospect Park / Prospect Park West"
git branch -f "G" HEAD
# Fort Hamilton Parkway / Prospect Avenue F G
git commit --allow-empty -m "Fort Hamilton Parkway / Prospect Avenue"
git branch -f "G" HEAD
# Church Avenue / McDonald Avenue F G
git commit --allow-empty -m "Church Avenue / McDonald Avenue"
git branch -f "G" HEAD
# Ditmas Avenue / McDonald Avenue F
git commit --allow-empty -m "Ditmas Avenue / McDonald Avenue"
# 18 Avenue / McDonald Avenue F
git commit --allow-empty -m "18 Avenue / McDonald Avenue"
# Avenue I / McDonald Avenue F
git commit --allow-empty -m "Avenue I / McDonald Avenue"
# Bay Parkway / McDonald Avenue F
git commit --allow-empty -m "Bay Parkway / McDonald Avenue"
# Avenue N / McDonald Avenue F
git commit --allow-empty -m "Avenue N / McDonald Avenue"
# Avenue P / McDonald Avenue F
git commit --allow-empty -m "Avenue P / McDonald Avenue"
# Kings Highway / McDonald Avenue F
git commit --allow-empty -m "Kings Highway / McDonald Avenue"
# Avenue U / McDonald Avenue F
git commit --allow-empty -m "Avenue U / McDonald Avenue"
# Avenue X / McDonald Avenue F
git commit --allow-empty -m "Avenue X / McDonald Avenue"
# Neptune Avenue / West 6 Street F
git commit --allow-empty -m "Neptune Avenue / West 6 Street"
# 7 Avenue / Flatbush Avenue B Q
git checkout "B"
git commit --allow-empty -m "7 Avenue / Flatbush Avenue"
git branch -f "Q" HEAD
# Prospect Park / Empire Boulevard - Flatbush Avenue B Q
git commit --allow-empty -m "Prospect Park / Empire Boulevard - Flatbush Avenue"
git branch -f "Q" HEAD
# Parkside Avenue / Ocean Avenue Q
git checkout "Q"
git commit --allow-empty -m "Parkside Avenue / Ocean Avenue"
# Church Avenue / East 18 Street B Q
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Church Avenue / East 18 Street" B
git branch -f "B" HEAD
# Beverley Road / East 16 Street Q
git commit --allow-empty -m "Beverley Road / East 16 Street"
# Cortelyou Road / East 16 Street Q
git commit --allow-empty -m "Cortelyou Road / East 16 Street"
# Newkirk Plaza / East 16 Street B Q
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Newkirk Plaza / East 16 Street" B
git branch -f "B" HEAD
# Avenue H / East 16 Street (Southbound only) Q
git commit --allow-empty -m "Avenue H / East 16 Street (Southbound only)"
# Avenue J / East 16 Street Q
git commit --allow-empty -m "Avenue J / East 16 Street"
# Avenue M / East 16 Street Q
git commit --allow-empty -m "Avenue M / East 16 Street"
# Kings Highway / East 16 Street B Q
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Kings Highway / East 16 Street" B
git branch -f "B" HEAD
# Avenue U / East 16 Street Q
git commit --allow-empty -m "Avenue U / East 16 Street"
# Neck Road / East 16 Street Q
git commit --allow-empty -m "Neck Road / East 16 Street"
# Sheepshead Bay / East 16 Street B Q
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Sheepshead Bay / East 16 Street" B
git branch -f "B" HEAD
# Brighton Beach / Brighton 6 Street B Q
git commit --allow-empty -m "Brighton Beach / Brighton 6 Street"
git branch -f "B" HEAD
# Ocean Parkway / Brighton Beach Avenue Q
git commit --allow-empty -m "Ocean Parkway / Brighton Beach Avenue"
# West 8 Street - New York Aquarium / Surf Avenue F Q
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "West 8 Street - New York Aquarium / Surf Avenue" F
git branch -f "F" HEAD
# Prospect Avenue / 4 Avenue D N R
git checkout "D"
git commit --allow-empty -m "Prospect Avenue / 4 Avenue"
git branch -f "R" HEAD
git branch -f "N" HEAD
# 25 Street / 4 Avenue D N R
git commit --allow-empty -m "25 Street / 4 Avenue"
git branch -f "R" HEAD
git branch -f "N" HEAD
# 36 Street / 4 Avenue D N R
git commit --allow-empty -m "36 Street / 4 Avenue"
git branch -f "R" HEAD
git branch -f "N" HEAD
# 9 Avenue / 39 Street D
git commit --allow-empty -m "9 Avenue / 39 Street"
# Fort Hamilton Parkway / New Utrecht Avenue D
git commit --allow-empty -m "Fort Hamilton Parkway / New Utrecht Avenue"
# 50 Street / New Utrecht Avenue D
git commit --allow-empty -m "50 Street / New Utrecht Avenue"
# 55 Street / New Utrecht Avenue D
git commit --allow-empty -m "55 Street / New Utrecht Avenue"
# 62 Street / New Utrecht Avenue D
git commit --allow-empty -m "62 Street / New Utrecht Avenue"
# 71 Street / New Utrecht Avenue D
git commit --allow-empty -m "71 Street / New Utrecht Avenue"
# 79 Street / New Utrecht Avenue D
git commit --allow-empty -m "79 Street / New Utrecht Avenue"
# 18 Avenue / New Utrecht Avenue D
git commit --allow-empty -m "18 Avenue / New Utrecht Avenue"
# 20 Avenue / 86 Street D
git commit --allow-empty -m "20 Avenue / 86 Street"
# Bay Parkway / 86 Street D
git commit --allow-empty -m "Bay Parkway / 86 Street"
# 25 Avenue / 86 Street D
git commit --allow-empty -m "25 Avenue / 86 Street"
# Bay 50 Street / Stillwell Avenue D
git commit --allow-empty -m "Bay 50 Street / Stillwell Avenue"
# 45 Street / 4 Avenue N R
git checkout "N"
git commit --allow-empty -m "45 Street / 4 Avenue"
git branch -f "R" HEAD
# 53 Street / 4 Avenue N R
git commit --allow-empty -m "53 Street / 4 Avenue"
git branch -f "R" HEAD
# 59 Street / 4 Avenue N R
git commit --allow-empty -m "59 Street / 4 Avenue"
git branch -f "R" HEAD
# Bay Ridge Avenue / 4 Avenue R
git checkout "R"
git commit --allow-empty -m "Bay Ridge Avenue / 4 Avenue"
# 77 Street / 4 Avenue R
git commit --allow-empty -m "77 Street / 4 Avenue"
# 86 Street / 4 Avenue R
git commit --allow-empty -m "86 Street / 4 Avenue"
# Bay Ridge - 95 Street / 4 Avenue R
git commit --allow-empty -m "Bay Ridge - 95 Street / 4 Avenue"
# World Trade Center / Church E
git checkout "E"
git commit --allow-empty -m "World Trade Center / Church"
# Cortlandt Street / West Broadway TEMPORARILY CLOSED 1
git checkout "1"
git commit --allow-empty -m "Cortlandt Street / West Broadway TEMPORARILY CLOSED"
# Rector Street 1
git commit --allow-empty -m "Rector Street"
# President Street / Nostrand Avenue 2 5
git checkout "2"
git commit --allow-empty -m "President Street / Nostrand Avenue"
git branch -f "5" HEAD
# Sterling Street / Nostrand Avenue 2 5
git commit --allow-empty -m "Sterling Street / Nostrand Avenue"
git branch -f "5" HEAD
# Winthrop Street / Nostrand Avenue 2 5
git commit --allow-empty -m "Winthrop Street / Nostrand Avenue"
git branch -f "5" HEAD
# Church Avenue / Nostrand Avenue 2 5
git commit --allow-empty -m "Church Avenue / Nostrand Avenue"
git branch -f "5" HEAD
# Beverly Road / Nostrand Avenue 2 5
git commit --allow-empty -m "Beverly Road / Nostrand Avenue"
git branch -f "5" HEAD
# Newkirk Avenue / Nostrand Avenue 2 5
git commit --allow-empty -m "Newkirk Avenue / Nostrand Avenue"
git branch -f "5" HEAD
# Flatbush Avenue - Brooklyn College / Nostrand Avenue 2 5
git commit --allow-empty -m "Flatbush Avenue - Brooklyn College / Nostrand Avenue"
git branch -f "5" HEAD
# Lafayette Avenue / Fulton Street A C
git checkout "A"
git commit --allow-empty -m "Lafayette Avenue / Fulton Street"
git branch -f "C" HEAD
# Clinton - Washington Avenues / Fulton Street A C
git commit --allow-empty -m "Clinton - Washington Avenues / Fulton Street"
git branch -f "C" HEAD
# Franklin Avenue / Fulton Street A C
git commit --allow-empty -m "Franklin Avenue / Fulton Street"
git branch -f "C" HEAD
# Nostrand Avenue / Fulton Street A C
git commit --allow-empty -m "Nostrand Avenue / Fulton Street"
git branch -f "C" HEAD
# Kingston - Throop Avenues / Fulton Street A C
git commit --allow-empty -m "Kingston - Throop Avenues / Fulton Street"
git branch -f "C" HEAD
# Utica Avenue / Fulton Street A C
git commit --allow-empty -m "Utica Avenue / Fulton Street"
git branch -f "C" HEAD
# Ralph Avenue / Fulton Street A C
git commit --allow-empty -m "Ralph Avenue / Fulton Street"
git branch -f "C" HEAD
# Rockaway Avenue / Fulton Street A C
git commit --allow-empty -m "Rockaway Avenue / Fulton Street"
git branch -f "C" HEAD
# 8 Avenue / 14 Street L
git checkout --orphan "L"
git commit --allow-empty -m "8 Avenue / 14 Street"
# 6 Avenue / 14 Street L
git commit --allow-empty -m "6 Avenue / 14 Street"
# 14 Street / Union Square L
git commit --allow-empty -m "14 Street / Union Square"
# 3 Avenue / 14 Street L
git commit --allow-empty -m "3 Avenue / 14 Street"
# 1 Avenue / 14 Street L
git commit --allow-empty -m "1 Avenue / 14 Street"
# Bedford Avenue / North 7 Street L
git commit --allow-empty -m "Bedford Avenue / North 7 Street"
# Lorimer Street / Metropolitan Avenue L
git commit --allow-empty -m "Lorimer Street / Metropolitan Avenue"
# Graham Avenue / Metropolitan Avenue L
git commit --allow-empty -m "Graham Avenue / Metropolitan Avenue"
# Grand Street / Bushwick Avenue L
git commit --allow-empty -m "Grand Street / Bushwick Avenue"
# Montrose Avenue / Bushwick Avenue L
git commit --allow-empty -m "Montrose Avenue / Bushwick Avenue"
# Morgan Avenue / Harrison Place L
git commit --allow-empty -m "Morgan Avenue / Harrison Place"
# Jefferson Street / Wyckoff Avenue L
git commit --allow-empty -m "Jefferson Street / Wyckoff Avenue"
# DeKalb Avenue / Wyckoff Avenue L
git commit --allow-empty -m "DeKalb Avenue / Wyckoff Avenue"
# Myrtle - Wyckoff Avenues L
git commit --allow-empty -m "Myrtle - Wyckoff Avenues"
# Halsey Street / Wyckoff Avenue L
git commit --allow-empty -m "Halsey Street / Wyckoff Avenue"
# Wilson Avenue / Moffat Street (Northbound Only) L
git commit --allow-empty -m "Wilson Avenue / Moffat Street (Northbound Only)"
# Bushwick Avenue - Aberdeen Street L
git commit --allow-empty -m "Bushwick Avenue - Aberdeen Street"
# Broadway Junction A C J L Z
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Broadway Junction" A C J Z
git branch -f "A" HEAD
git branch -f "C" HEAD
git branch -f "J" HEAD
git branch -f "Z" HEAD
# Chauncey Street / Broadway J
git checkout "J"
git commit --allow-empty -m "Chauncey Street / Broadway"
# Halsey Street / Broadway J
git commit --allow-empty -m "Halsey Street / Broadway"
# Gates Avenue / Broadway J Z
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Gates Avenue / Broadway" Z
git branch -f "Z" HEAD
# Kosciuszko Street / Broadway J
git commit --allow-empty -m "Kosciuszko Street / Broadway"
# Myrtle Avenue / Broadway J Z
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Myrtle Avenue / Broadway" Z
git branch -f "Z" HEAD
# Flushing Avenue / Broadway J
git commit --allow-empty -m "Flushing Avenue / Broadway"
# Lorimer Street / Broadway J
git commit --allow-empty -m "Lorimer Street / Broadway"
# Hewes Street / Broadway J
git commit --allow-empty -m "Hewes Street / Broadway"
# Marcy Avenue / Broadway J Z
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Marcy Avenue / Broadway" Z
git branch -f "Z" HEAD
# Essex Street / Delancey Street J M Z
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Essex Street / Delancey Street" M
git branch -f "M" HEAD
git branch -f "Z" HEAD
# Bowery / Delancey Street J Z
git commit --allow-empty -m "Bowery / Delancey Street"
git branch -f "Z" HEAD
# Canal Street / Centre Street J Z
git commit --allow-empty -m "Canal Street / Centre Street"
git branch -f "Z" HEAD
# Chambers Street / Centre Street J Z
git commit --allow-empty -m "Chambers Street / Centre Street"
git branch -f "Z" HEAD
# Broad Street / Wall Street J Z
git commit --allow-empty -m "Broad Street / Wall Street"
git branch -f "Z" HEAD
# 8 Avenue / 62 Street N
git checkout "N"
git commit --allow-empty -m "8 Avenue / 62 Street"
# Fort Hamilton Parkway / 62 Street N
git commit --allow-empty -m "Fort Hamilton Parkway / 62 Street"
# New Utrecht Avenue / 62 Street N
git commit --allow-empty -m "New Utrecht Avenue / 62 Street"
# 18 Avenue / 64 Street N
git commit --allow-empty -m "18 Avenue / 64 Street"
# 20 Avenue / 64 Street N
git commit --allow-empty -m "20 Avenue / 64 Street"
# Bay Parkway / West 7 Street N
git commit --allow-empty -m "Bay Parkway / West 7 Street"
# Kings Highway / West 7 Street N
git commit --allow-empty -m "Kings Highway / West 7 Street"
# Avenue U / West 7 Street N
git commit --allow-empty -m "Avenue U / West 7 Street"
# 86 Street / West 7 Street N
git commit --allow-empty -m "86 Street / West 7 Street"
# Coney Island - Stillwell Avenue / Surf Avenue D F N Q
git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m "Coney Island - Stillwell Avenue / Surf Avenue" D F Q
git branch -f "D" HEAD
git branch -f "F" HEAD
git branch -f "Q" HEAD
# Liberty Avenue / Pennsylvania Avenue A C
git checkout "A"
git commit --allow-empty -m "Liberty Avenue / Pennsylvania Avenue"
git branch -f "C" HEAD
# Van Siclen Avenue / Pitkin Avenue A C
git commit --allow-empty -m "Van Siclen Avenue / Pitkin Avenue"
git branch -f "C" HEAD
# Shepherd Avenue / Pitkin Avenue A C
git commit --allow-empty -m "Shepherd Avenue / Pitkin Avenue"
git branch -f "C" HEAD
# Euclid Avenue / Pitkin Avenue A C
git commit --allow-empty -m "Euclid Avenue / Pitkin Avenue"
git branch -f "C" HEAD
# Grant Avenue / Pitkin Avenue A
git commit --allow-empty -m "Grant Avenue / Pitkin Avenue"
# 80 Street / Liberty Avenue A
git commit --allow-empty -m "80 Street / Liberty Avenue"
# 88 Street / Liberty Avenue A
git commit --allow-empty -m "88 Street / Liberty Avenue"
# Rockaway Boulevard / Liberty Avenue A
git commit --allow-empty -m "Rockaway Boulevard / Liberty Avenue"
# Atlantic Avenue / Snediker Avenue / Van Sinderen Avenue L
git checkout "L"
git commit --allow-empty -m "Atlantic Avenue / Snediker Avenue / Van Sinderen Avenue"
# Sutter Avenue / Van Sinderen Avenue L
git commit --allow-empty -m "Sutter Avenue / Van Sinderen Avenue"
# Livonia Avenue / Van Sinderen Avenue L
git commit --allow-empty -m "Livonia Avenue / Van Sinderen Avenue"
# New Lots Avenue / Van Sinderen Avenue L
git commit --allow-empty -m "New Lots Avenue / Van Sinderen Avenue"
# East 105 Street / Turnbull Avenue L
git commit --allow-empty -m "East 105 Street / Turnbull Avenue"
# Canarsie - Rockaway Parkway / Glenwood Road L
git commit --allow-empty -m "Canarsie - Rockaway Parkway / Glenwood Road"
# Forest Avenue / 67 Avenue M
git checkout "M"
git commit --allow-empty -m "Forest Avenue / 67 Avenue"