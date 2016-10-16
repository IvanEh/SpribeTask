var baseUrl = "api/";

$(document).ready(function() {
    var submitAddReady = $('#add-ready');
    var submitFindOpponent = $('#find-opponent');
    var name = $('#opponent-finder-form input[name="player"]');
    var rating = $('#opponent-finder-form input[name="rating"]');

    submitAddReady.click(function() {
        submitAddReady.prop('disabled', true);
        $.ajax(
        {
            url: baseUrl + "ready-players",
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                name: name.val(),
                rating: rating.val()
            })
        })
        .done(function(data) {
            submitAddReady.prop('disabled', false);
            updateReadyUsers();
        })
        .fail(function (xhr) {
            submitFindOpponent.prop('disabled', false);
        });;
    })

    submitFindOpponent.click(function() {
        submitFindOpponent.prop('disabled', true);
        $.ajax(
        {
            url: baseUrl + "ready-players/opponent",
            method: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify({
                name: name.val(),
                rating: rating.val()
            })
        })
        .done(function(data) {
            submitAddReady.prop('disabled', false);
            updateOpponentInfo(data);
        })
        .fail(function (xhr) {
            submitAddReady.prop('disabled', false);
        });
    })

    function updateReadyUsers() {
        $.ajax(
        {
            url: baseUrl + "ready-players",
            method: 'GET',
        })
        .done(function(data) {
            $('#ready-players').html(JSON.stringify(data));
        });
    }

    function updateOpponentInfo(data) {
        $('#opponent').show();
        $('#opponent > span').html(JSON.stringify(data));

        updateReadyUsers();
    }
});
