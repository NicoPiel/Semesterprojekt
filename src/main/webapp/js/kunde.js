$(document).on('click', '#scanner-button', function () {
    $('#customer-card').trigger('ticket:scanned');
});

$(document).on('ticket:scanned', '#customer-card', function (){
    console.log('ticket scanned');

    $.ajax({
        type: 'POST',
        url: "servlet",
        data: JSON.stringify({
            "data": "cars"
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (message) {
            let cars = message.data.cars;
            let rand = getRandomInt(0, cars.length);

            let car = cars[rand];

            let id = car.id;
            let category = car.category;
            let enterTimestamp = car.enterTimestamp;

            let html = '<div class="card bg-light border-dark mb-3" id="customer-card">';
            html += '<div class="card-header" id="customer-card-header">';
            html += 'Parkplatz ID: ' + id;
            html += '</div>';
            html += '<div class="card-body" id="customer-card-body">';
            html += 'Kategorie: ' + category;
            html += '<hr/>';
            html += 'Einfahrt um ' + new Date(enterTimestamp).toLocaleTimeString() + ' Uhr';
            html += '</div>';
            html += '</div>';

            $.ajaxSetup({
                cache: false
            });

            $('#customer-card').replaceWith(html).load('#');
        },
        error: function (e) {
            console.log(e);
        }
    });
});

// Returns a random integer between min (inclusive) and max (exclusive)
function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.trunc(Math.random() * (max - min)) + min;
}