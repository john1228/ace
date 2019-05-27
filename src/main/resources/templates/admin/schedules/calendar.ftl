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
                left: '',
                center: 'title',
                right: ''
            },
            defaultView: 'timeGridWeek',
            themeSystem: 'bootstrap',
            locale: 'zh-cn',
            defaultDate: new Date(),
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
                data.data.forEach(function (item) {
                    calendar.gotoDate($("#date").val());
                    calendar.addEvent(
                            {
                                title: item.contactName + ' : ' + item.contactMobile,
                                start: item.startTime,
                                end: item.endTime,
                                description: '联系人:' + item.contactName + '\n' + '联系电话:' + item.contactMobile,
                                url: "/admin/orders/" + item.orderId + "",
                                textColor: "white",
                                textEscape: true
                            }
                    )
                })
            })
        })
    })
</script>