requirejs.config({
    baseUrl: 'js'
})

function buildRoute(view) {
    return function () {
        webix.ui({
            id: 'root',
            rows: [
                view
            ]
        }, $$('root'))
    }
}

function buildButton(label, route) {
    return {
        view: "button",
        value: label,
        width: 100,
        align: 'center',
        click: function () {
            routie(route)
        }
    }
}

require(['views/main', 'views/employees', 'util/resourceProxy'],
    function (main, employees, resourceProxy) {
        webix.ready(function () {
            webix.ui({
                container: 'app',
                width: document.body.clientWidth,
                height: document.body.clientHeight,
                rows: [
                    {
                        view: "toolbar",
                        cols: [
                            buildButton('Home', ''),
                            buildButton('Employees', 'employees')
                        ]
                    },
                    {
                        id: 'root'
                    }
                ]
            })
        })

        routie({
            '': buildRoute(main),
            'employees': buildRoute(employees)
        })
    })