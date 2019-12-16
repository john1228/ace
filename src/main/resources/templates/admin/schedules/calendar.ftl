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
    $.ajaxSetup({headers: {'X-CSRF-Token': $('meta[name="_csrf"]').attr('content')}});
    document.addEventListener('DOMContentLoaded', function () {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            plugins: ['dayGrid', 'timeGrid', 'list', 'interaction'],
            header: {
                left: 'title',
                center: '',
                right: 'today prev,next'
            },
            defaultView: 'timeGridWeek',
            themeSystem: 'bootstrap',
            locale: 'zh-cn',
            businessHours: {
                daysOfWeek: [1, 2, 3, 4, 5, 6, 7],
                startTime: "08:00",
                endTime: "21:00"
            },
            eventColor: "#FEA8A9"
        });
        calendar.render();
        $('#query').on("click", function () {
            $.post("/admin/schedules", {
                roomId: $("#room").val(),
                date: $("#date").val()
            }, function (data) {
                calendar.gotoDate($("#date").val());
                calendar.removeAllEvents();
                data.data.forEach(function (item) {
                    calendar.addEvent(
                            {
                                title: '联系人:' + item.contactName + '　' + '联系电话:' + item.contactMobile,
                                start: item.startTime,
                                end: item.endTime,
                                url: "/admin/orders/" + item.orderNo + "",
                                textColor: "white",
                                textEscape: true
                            }
                    )
                })
            })
        })
    })
</script>