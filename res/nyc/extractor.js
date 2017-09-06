const fs = require('fs');
const cheerio = require('cheerio');

const BLACKLIST = ['Subway Transfers', 'Stations / Cross Streets',
                   'BRONX', 'MANHATTAN', 'QUEENS', 'BROOKLYN',
                   'Stations /  Cross Streets'];

const NOTIFICATION_WORDS = [
  "\\( Only\\)", "not in service", "Accessibility", "Beginning",
  "Street Elevator", "There  is no elevator", "There is no", "Only  line", "Station Closed"
]

const wrongStationsContent = fs.readFileSync(`${__dirname}/wrong_stations.json`, 'utf-8');
const WRONG_STATIONS = JSON.parse(wrongStationsContent);

const filterStation = (station) => {
  station = station.split("/").map(s => s.trim()).join(" / ");
  station = station.split("-").map(s => s.trim()).join(" - ");

  station = station.replace('St ', 'Street ');

  const notification_words = NOTIFICATION_WORDS.map(e => `${e}.*`).join('|');
  const regex = new RegExp(notification_words);
  station = station.replace(regex, '');

  station = WRONG_STATIONS.reduce((resultedStation, wrongStation) => {
    const wrongStationRegex = new RegExp(wrongStation.wrong.join('|'))
    return resultedStation.replace(wrongStationRegex, wrongStation.right)
  }, station);

  return station.trim();
};

const findStations = (fileName) => {
  const file = `${__dirname}/${fileName}.htm`;

  const contents = fs.readFileSync(file, 'utf-8');

  // Skipping S line because it does not have continuity
  if (fileName == 'S') {
    return null;
  }

  const $ = cheerio.load(contents);

  let table; 
  if (fileName !== '5') {
    table = $(`table[summary='Table of ${fileName} Subway Line Stops']`).first();

  } else {
    table = $(`table[summary='Table of ${fileName} Subway Line Stops']`).eq(2);
  }

  const stations = table.find('.emphasized').parent().map((i, elem) => {
    return $(elem) .text().split('\n')
      .map((word) => {
        return word.trim();
      })
      .filter((word) => {
        return word != '' ;
      })
      .map((word) => {
        return word.replace('\*', '');
      }) .join(' ');
  }).filter((i, el) => {
    return !BLACKLIST.includes(el)
  }).get();

  return stations.map(s => filterStation(s));
}

const allStations = (dirName) => {
  const files = fs.readdirSync(dirName)
  return files
    .filter((file) => { return file.substr(-4) === '.htm' })
    .map((f) => f.substr(0, 1))
    .map((line) => { return {line: line, stations: findStations(line)} });
}

const stations = allStations(__dirname)
      .filter(n => n.stations)
      .map((line) => [`*${line.line}`, ...line.stations]);
// Flattenig and joining with a breakline
const contents = [].concat.apply([], stations).join('\n');

fs.writeFile(`${__dirname}/../nyc.txt`, contents);
