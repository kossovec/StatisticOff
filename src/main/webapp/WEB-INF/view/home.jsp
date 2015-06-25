<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<html>
<head>
    <link href="/resources/css/bootstrap.css" rel="stylesheet"/>
    <link href="/resources/css/select2.css" rel="stylesheet"/>
    <link href="/resources/css/select2-bootstrap.css" rel="stylesheet"/>
    <link href="/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
    <link href="/resources/css/styles.css" rel="stylesheet"/>


    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/select2.js"></script>
    <script src="/resources/js/moment.js"></script>
    <script src="/resources/js/transition.js"></script>
    <script src="/resources/js/collapse.js"></script>
    <script src="/resources/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/resources/js/ru.js"></script>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script>
        $(document).ready(function () {
            $(".select-beast").select2({width: '100%'});
        });
    </script>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker2').datetimepicker({
//                locale: 'ru'
            });
        });
    </script>
    <script>
        $('document').ready(function () {
            $('#modal').modal();
        });
    </script>


</head>
<body>
<nav class="navbar navbar-inverse fix">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#myNavbar">
                <span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/StatisticOff/">StatisticOff</a>
        </div>
    </div>
</nav>

<div class="row-fluid">
    <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Statistic OFF</h3>
            </div>
                <div class="panel-body">
                <form role="form" name="loginForm" action="/StatisticOff/off" method='post'>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <c:out value="${error}"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <c:out value="${message}"/>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <select class="select-beast" placeholder="Select NE....." , name="name">
                            <option value="">Select NE.....</option>
                            <c:forEach items="${nes}" var="n">
                                <option value="<c:out value="${n.name}"/>"><c:out value="${n.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <div class='input-group date' id='datetimepicker2'>
                            <input type='text' class="form-control" name="date" placeholder="Select date and time of statistics enable"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                        </div>
                    </div>

                    <input type="submit" value="Off It"
                           class="btn btn-info btn-block">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
