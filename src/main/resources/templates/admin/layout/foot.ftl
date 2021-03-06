<script src="/js/default/index.js"></script>
<script src="/js/ajax-file-uploader.js"></script>
<script src="/js/jquery.Jcrop.js"></script>
<script src="/js/comet4j.js"></script>
<script src="/js/e-charts.min.js"></script>
<script src="/assets/js/bootstrap/min.js"></script>
<script src="/assets/js/jquery/type-ahead.js"></script>

<!-- page specific plugin scripts -->
<script src="/assets/js/jquery-ui/custom.min.js"></script>
<script src="/assets/js/jquery-ui/touch-punch.min.js"></script>

<script src="/assets/js/jquery/chosen.min.js"></script>
<script src="/assets/js/fuelux/spinner.js"></script>
<script src="/assets/js/bootstrap/moment.min.js"></script>
<script src="/assets/js/bootstrap/datepicker.min.js"></script>
<script src="/assets/js/bootstrap/datetimepicker.min.js"></script>

<script src="/assets/js/bootstrap/date-range-picker.min.js"></script>
<script src="/assets/js/bootstrap/color-picker.min.js"></script>
<script src="/assets/js/jquery/knob.min.js"></script>
<script src="/assets/js/jquery/autosize.min.js"></script>
<script src="/assets/js/jquery/input-limiter.min.js"></script>
<script src="/assets/js/jquery/masked-input.min.js"></script>

<script src="/assets/js/markdown/markdown.min.js"></script>
<script src="/assets/js/markdown/bootstrap-markdown.min.js"></script>
<script src="/assets/js/jquery/hotkeys.min.js"></script>
<script src="/assets/js/bootstrap/wysiwyg.min.js"></script>
<script src="/assets/js/bootstrap/box.min.js"></script>

<script src="/assets/js/jquery/easy-pie-chart.min.js"></script>
<script src="/assets/js/jquery/gritter.min.js"></script>
<!-- ace scripts -->
<script src="/assets/js/ace/elements.min.js"></script>
<script src="/assets/js/ace/min.js"></script>

<!-- tree -->
<#--<script src="/assets/js/fuelux/tree.min.js"></script>-->
<script src="http://www.fuelcdn.com/fuelux/3.7.3/js/fuelux.min.js"></script>
<script type="text/javascript" inline="javascript">
    $(document).ready(function () {
        $(".table").on("click", 'a[data-method="DELETE"]', function (e) {
            var message = $(this).data("message");
            var confirmMsg = $(this).data("confirm");
            if (confirmMsg != undefined) {
                if (confirm(confirmMsg))
                    $.ajax({
                        url: $(this).attr("href"), type: 'DELETE', data: {}, success: function (data) {
                            if (data == "SUCCESS") {
                                layer.msg(message, function () {
                                    window.location.reload();
                                })
                            }
                        }
                    })
            }
            e.preventDefault();
        });
        $(".table").on("click", 'a[data-method="POST"]', function (e) {
            var message = $(this).data("message");
            e.preventDefault();
            $.ajax({
                url: $(this).attr("href"), type: 'POST', data: {}, success: function (data) {
                    if (data == "SUCCESS") {
                        layer.msg(message, function () {
                            window.location.reload();
                        })
                    }
                }
            })
        });

        $('[data-toggle="buttons"] .btn').on('click', function (e) {
            var target = $(this).find('input[type=radio]');
            var which = parseInt(target.val());
            var toolbar = $('#editor1').prev().get(0);
            if (which == 1 || which == 2 || which == 3) {
                toolbar.className = toolbar.className.replace(/wysiwyg\-style(1|2)/g, '');
                if (which == 1) $(toolbar).addClass('wysiwyg-style1');
                else if (which == 2) $(toolbar).addClass('wysiwyg-style2');
            }
        });
        if (typeof jQuery.ui !== 'undefined' && /applewebkit/.test(navigator.userAgent.toLowerCase())) {
            var lastResizableImg = null;

            function destroyResizable() {
                if (lastResizableImg == null) return;
                lastResizableImg.resizable("destroy");
                lastResizableImg.removeData('resizable');
                lastResizableImg = null;
            }

            var enableImageResize = function () {
                $('.wysiwyg-editor')
                        .on('mousedown', function (e) {
                            var target = $(e.target);
                            if (e.target instanceof HTMLImageElement) {
                                if (!target.data('resizable')) {
                                    target.resizable({
                                        aspectRatio: e.target.width / e.target.height,
                                    });
                                    target.data('resizable', true);

                                    if (lastResizableImg != null) {//disable previous resizable image
                                        lastResizableImg.resizable("destroy");
                                        lastResizableImg.removeData('resizable');
                                    }
                                    lastResizableImg = target;
                                }
                            }
                        })
                        .on('click', function (e) {
                            if (lastResizableImg != null && !(e.target instanceof HTMLImageElement)) {
                                destroyResizable();
                            }
                        })
                        .on('keydown', function () {
                            destroyResizable();
                        });
            }
            enableImageResize();
        }

        if (!ace.vars['touch']) {
            $('.chosen-select').chosen({allow_single_deselect: true});
            $(window)
                    .off('resize.chosen')
                    .on('resize.chosen', function () {
                        $('.chosen-select').each(function () {
                            var $this = $(this);
                            $this.next().css({'width': $this.parent().width()});
                        })
                    }).trigger('resize.chosen');
            $(document).on('settings.ace.chosen', function (e, event_name, event_val) {
                if (event_name != 'sidebar_collapsed') return;
                $('.chosen-select').each(function () {
                    var $this = $(this);
                    $this.next().css({'width': $this.parent().width()});
                })
            });


            $('#chosen-multiple-style .btn').on('click', function (e) {
                var target = $(this).find('input[type=radio]');
                var which = parseInt(target.val());
                if (which == 2) $('#form-field-select-4').addClass('tag-input-style');
                else $('#form-field-select-4').removeClass('tag-input-style');
            });
        }


        if (!ace.vars['old_ie']) $('.datetimepicker').datetimepicker({
            format: 'YYYY-MM-DD hh:mm',
            icons: {
                time: 'fa fa-clock-o',
                date: 'fa fa-calendar',
                up: 'fa fa-chevron-up',
                down: 'fa fa-chevron-down',
                previous: 'fa fa-chevron-left',
                next: 'fa fa-chevron-right',
                today: 'fa fa-arrows ',
                clear: 'fa fa-trash',
                close: 'fa fa-times'
            }
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });
    /*]]>*/
</script>