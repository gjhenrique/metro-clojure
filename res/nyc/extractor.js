const fs = require('fs');
const cheerio = require('cheerio');

const BLACKLIST = ['Subway Transfers', 'Stations / Cross Streets',
                   'BRONX', 'MANHATTAN', 'QUEENS', 'BROOKLYN',
                   'Stations /  Cross Streets'];

const findStations = (fileName) => {
  const file = `${__dirname}/${fileName}.htm`;

  const contents = fs.readFileSync(file, 'utf-8');

  var $ = cheerio.load(contents);
  const table = $(`table[summary='Table of ${fileName} Subway Line Stops']`).first();

  return table.find('.emphasized').parent().map((i, elem) => {
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
}

const allStations = (dirName) => {
  const files = fs.readdirSync(dirName)
  return files
    .filter((file) => { return file.substr(-4) === '.htm' })
    .map((f) => f.substr(0, 1))
    .map((line) => { return {line: line, stations: findStations(line)} });
}

const stations = allStations(__dirname)
      .map((line) => [`*${line.line}`, ...line.stations]);
// Flattenig and joining with a breakline
const contents = [].concat.apply([], stations).join('\n');

fs.writeFile(`${__dirname}/../nyc.txt`, contents);
