<script src="/assets/js/fullcalendar/main.js"></script>
<script src="/assets/js/fullcalendar/locales-all.js"></script>
<script src="/assets/js/fullcalendar/interaction/main.js"></script>
<script src="/assets/js/fullcalendar/list/main.js"></script>
<script src="/assets/js/fullcalendar/daygrid/main.js"></script>
<script src="/assets/js/fullcalendar/timegrid/main.js"></script>

<link rel="stylesheet" href="/assets/css/fullcalendar/main.css"/>
<link rel="stylesheet" href="/assets/css/fullcalendar/list/main.css"/>
<link rel="stylesheet" href="/assets/css/fullcalendar/daygrid/main.css"/>
<link rel="stylesheet" href="/assets/css/fullcalendar/timegrid/main.css"/>

<div id="calendar" class="col-xs-12"></div>
<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function () {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            plugins: ['dayGrid', 'timeGrid', 'list', 'interaction'],
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
            },
            themeSystem: 'bootstrap',
            locale: 'zh-cn',
            defaultDate: new Date(),
            businessHours: true,
            events: [
                {
                    title: 'All Day Event',
                    start: '2019-04-01'
                },
                {
                    title: 'Long Event',
                    start: '2019-04-07',
                    end: '2019-04-10'
                },
                {
                    groupId: 999,
                    title: 'Repeating Event',
                    start: '2019-04-09T16:00:00'
                },
                {
                    groupId: 999,
                    title: 'Repeating Event',
                    start: '2019-04-16T16:00:00'
                },
                {
                    title: 'Conference',
                    start: '2019-04-11',
                    end: '2019-04-13'
                },
                {
                    title: 'Meeting',
                    start: '2019-04-12T10:30:00',
                    end: '2019-04-12T12:30:00'
                },
                {
                    title: 'Lunch',
                    start: '2019-04-12T12:00:00'
                },
                {
                    title: 'Meeting',
                    start: '2019-04-12T14:30:00'
                },
                {
                    title: 'Happy Hour',
                    start: '2019-04-12T17:30:00'
                },
                {
                    title: 'Dinner',
                    start: '2019-04-12T20:00:00'
                },
                {
                    title: 'Birthday Party',
                    start: '2019-04-13T07:00:00'
                },
                {
                    title: 'Click for Google',
                    url: 'http://google.com/',
                    start: '2019-04-28'
                }
            ]
        });
        calendar.render();
    })
</script>