let cars;
let carCategories;

// Do things when the page has been loaded
$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();

    $('#car-list').DataTable({
        responsive: true,
        order: [[ 0, "asc" ]],
        scrollY: '50vh',
        scrollCollapse: true,
        paging: false,
        search: false,
        searchable: false,
        searching: false,
        info: false,
    });

    cars = [];

    // Start simulation
    $.ajax({
        type: 'POST',
        url: "servlet",
        data: JSON.stringify({
            "action": "simulation_start"
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (message) {
            console.log('Simulation started.');
        },
        error: function (e) {
            console.log(e);
        }
    });

    // Do this every 500ms
    window.setInterval(function () {
        // Fetch metadata
        $.ajax({
            type: 'POST',
            url: "servlet",
            data: JSON.stringify({
                "data": "meta"
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (message) {
                //console.log(message);

                carCategories = [];
                let html = '<tbody id="data-table-body">';

                html += '<tr>';
                html += '<td> Gesamtkapazität: ' + message.data.capacity + ' </td>';
                html += '</tr>';

                html += '<tr>';
                html += '<td> Belegt: ' + message.data.occupied + ' </td>';
                html += '</tr>';

                html += '<tr>';
                html += '<td> Preis: ' + message.data.price + ' </td>';
                html += '</tr>';

                let catHtml = '<tbody id="categories-table-body">';

                message.data.categories.forEach(function (current, index) {
                    carCategories.push(message.data.categories[index].identifier);

                    catHtml += '<tr>';
                    catHtml += '<th scope="row">' + (index + 1) + ' </th>';

                    catHtml += '<td>';
                    catHtml += message.data.categories[index].identifier;
                    catHtml += '</td>';

                    catHtml += '<td>';
                    catHtml += message.data.categories[index].capacity;
                    catHtml += '</td>';

                    catHtml += '<td>';
                    catHtml += message.data.categories[index].occupied;
                    catHtml += '</td>';
                    catHtml += '</tr>';
                });

                catHtml += '</tbody>';
                html += '</tbody>';

                $('#data-table-body').replaceWith(html).load('#');
                $('#categories-table-body').replaceWith(catHtml).load('#');
            },
            error: function (e) {
                console.log(e);
            }
        });

        // Fetch car info
        $.ajax({
            type: 'POST',
            url: "servlet",
            data: JSON.stringify({
                "data": "cars"
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (message) {
                //console.log(message);

                let data = message.data;
                cars = data.cars;

                $('#car-table-body').trigger('table:reload');
            },
            error: function (e) {
                console.log(e);
            }
        });
    }, 500);

    // Clock
    window.setInterval(function () {
        let d = new Date();
        let t = d.toLocaleTimeString();
        $("#timeClock").html(t);
    }, 500);
});

// Trigger this to reload the car info table
$(document).on("table:reload", '#car-table-body', function (event) {
    let html = '<tbody id="car-table-body">';

    cars.forEach(function (current) {
        html += '<tr>';
        html += '<td> ' + current.id + ' </td>';
        html += '<td> ' + current.category + ' </td>';
        html += '<td> <div data-toggle="tooltip" data-placement="top" title="' + new Date(current.enterTimestamp).toLocaleDateString() + '">' + new Date(current.enterTimestamp).toLocaleTimeString() + ' Uhr </div></td>';
        html += '</tr>';
    });

    html += '</tbody>';

    $.ajaxSetup({
        cache: false
    });

    $('#car-table-body').replaceWith(html).load('#');
});

// Returns a random integer between min (inclusive) and max (exclusive)
function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.trunc(Math.random() * (max - min)) + min;
}

// Returns a random integer between min (inclusive) and max (inclusive)
function getRandomIntInclusive(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.trunc(Math.random() * (max - min + 1)) + min;
}