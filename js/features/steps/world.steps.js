const assert = require('assert')
const world = require('../world')
const {When, Then} = require('cucumber')

When('I set the World object {string} to {string}', function (keyString, valueString) {
    world[keyString] = valueString
})

Then('I should get the World object {string} as {string}', function (keyString, expectedString) {
    assert.equal(world[keyString], expectedString)
})