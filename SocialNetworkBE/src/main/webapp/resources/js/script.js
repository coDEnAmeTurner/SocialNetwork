function enableReport(type) {
    const startYear = document.getElementById("start-year");
    const endYear = document.getElementById("end-year");
    const myear = document.getElementById("myear");
    const qyear = document.getElementById("qyear");
    const buttonMonth = document.getElementById("button-month");
    const buttonYear = document.getElementById("button-year");
    const buttonQuarter = document.getElementById("button-quarter");

    if (type === 'year') {
        startYear.disabled = false;
        endYear.disabled = false;
        buttonYear.disabled = false;
        buttonMonth.disabled = true;
        buttonQuarter.disabled = true;
        myear.disabled = true;
        qyear.disabled = true;
    }

    if (type === 'month') {
        startYear.disabled = true;
        endYear.disabled = true;
        myear.disabled = false;
        buttonYear.disabled = true;

        buttonMonth.disabled = false;
        buttonQuarter.disabled = true;

        qyear.disabled = true;

    }

    if (type === 'quarter') {
        startYear.disabled = true;
        endYear.disabled = true;
        myear.disabled = true;
        qyear.disabled = false;
        buttonQuarter.disabled = false;
        buttonMonth.disabled = true;
        buttonYear.disabled = true;

    }
}

function generateUserStats(type) {
    if (type === 'year') {
        const sy = document.getElementById("start-year").value;
        const ey = document.getElementById("end-year").value;

        fetch(`http://localhost:8288/SocialNetworkBE/api/users/count-users-by-year/?startYear=${sy}&endYear=${ey}`, {
            method: 'GET',
        }).then(res => {
            if (!res.ok) {
                throw new Error('Không thể thống kê được!!!');
            }

            return res.json();
        }).then(counts => {
            const userChartDiv = document.getElementById("userChartDiv");
            while (userChartDiv.hasChildNodes())
                userChartDiv.removeChild(userChartDiv.childNodes[0])

            const canvas = document.createElement("canvas");

            let labels = counts.map(count => {
                return count[0]
            });
            let cdata = counts.map(count => {
                return count[1]
            });

            drawBarChart(
                    canvas,
                    labels = labels,
                    data = cdata,
                    title = `User report from ${sy} to ${ey}`,
                    legendPos = 'bottom'
                    );
            userChartDiv.appendChild(canvas);
        })
    }

    if (type === 'month') {
        const y = document.getElementById("myear").value;

        fetch(`http://localhost:8288/SocialNetworkBE/api/users/count-users-by-month/?year=${y}`, {
            method: 'GET',
        }).then(res => {
            if (!res.ok) {
                throw new Error('Không thể thống kê được!!!');
            }

            return res.json();
        }).then(counts => {
            const userChartDiv = document.getElementById("userChartDiv");
            while (userChartDiv.hasChildNodes())
                userChartDiv.removeChild(userChartDiv.childNodes[0])

            const canvas = document.createElement("canvas");

            let labels = counts.map(count => {
                return count[1]
            });
            let cdata = counts.map(count => {
                return count[2]
            });

            drawBarChart(
                    canvas,
                    labels = labels,
                    data = cdata,
                    title = `User report by months of the year ${y}`,
                    legendPos = 'bottom'
                    );
            userChartDiv.appendChild(canvas);
        })
    }

    if (type === 'quarter') {
        const y = document.getElementById("qyear").value;

        fetch(`http://localhost:8288/SocialNetworkBE/api/users/count-users-by-quarter/?year=${y}`, {
            method: 'GET',
        }).then(res => {
            if (!res.ok) {
                throw new Error('Không thể thống kê được!!!');
            }

            return res.json();
        }).then(counts => {
            const userChartDiv = document.getElementById("userChartDiv");
            while (userChartDiv.hasChildNodes())
                userChartDiv.removeChild(userChartDiv.childNodes[0])

            const canvas = document.createElement("canvas");

            let labels = counts.map(count => {
                return count[1]
            });
            let cdata = counts.map(count => {
                return count[2]
            });

            drawBarChart(
                    canvas,
                    labels = labels,
                    data = cdata,
                    title = `User report by quarters of the year ${y}`,
                    legendPos = 'bottom'
                    );
            userChartDiv.appendChild(canvas);
        })
    }
}

function enablePostReport(type) {
    const startYear = document.getElementById("start-post-year");
    const endYear = document.getElementById("end-post-year");
    const myear = document.getElementById("post-myear");
    const qyear = document.getElementById("post-qyear");
    const buttonMonth = document.getElementById("button-post-month");
    const buttonYear = document.getElementById("button-post-year");
    const buttonQuarter = document.getElementById("button-post-quarter");

    if (type === 'year') {
        startYear.disabled = false;
        endYear.disabled = false;
        buttonYear.disabled = false;
        buttonMonth.disabled = true;
        buttonQuarter.disabled = true;
        myear.disabled = true;
        qyear.disabled = true;
    }

    if (type === 'month') {
        startYear.disabled = true;
        endYear.disabled = true;
        myear.disabled = false;
        buttonYear.disabled = true;

        buttonMonth.disabled = false;
        buttonQuarter.disabled = true;

        qyear.disabled = true;

    }

    if (type === 'quarter') {
        startYear.disabled = true;
        endYear.disabled = true;
        myear.disabled = true;
        qyear.disabled = false;
        buttonQuarter.disabled = false;
        buttonMonth.disabled = true;
        buttonYear.disabled = true;

    }
}

function generatePostStats(type) {
    if (type === 'year') {
        const sy = document.getElementById("start-post-year").value;
        const ey = document.getElementById("end-post-year").value;

        fetch(`http://localhost:8288/SocialNetworkBE/api/posts/count-posts-by-year/?startYear=${sy}&endYear=${ey}`, {
            method: 'GET',
        }).then(res => {
            if (!res.ok) {
                throw new Error('Không thể thống kê được!!!');
            }

            return res.json();
        }).then(counts => {
            const postChartDiv = document.getElementById("postChartDiv");
            while (postChartDiv.hasChildNodes())
                postChartDiv.removeChild(postChartDiv.childNodes[0])

            const canvas = document.createElement("canvas");

            let labels = counts.map(count => {
                return count[0]
            });
            let cdata = counts.map(count => {
                return count[1]
            });

            drawBarChart(
                    canvas,
                    labels = labels,
                    data = cdata,
                    title = `posts report from ${sy} to ${ey}`,
                    legendPos = 'bottom'
                    );
            postChartDiv.appendChild(canvas);
        })
    }

    if (type === 'month') {
        const y = document.getElementById("post-myear").value;

        fetch(`http://localhost:8288/SocialNetworkBE/api/posts/count-posts-by-month/?year=${y}`, {
            method: 'GET',
        }).then(res => {
            if (!res.ok) {
                throw new Error('Không thể thống kê được!!!');
            }

            return res.json();
        }).then(counts => {
            const postChartDiv = document.getElementById("postChartDiv");
            while (postChartDiv.hasChildNodes())
                postChartDiv.removeChild(postChartDiv.childNodes[0])

            const canvas = document.createElement("canvas");

            let labels = counts.map(count => {
                return count[1]
            });
            let cdata = counts.map(count => {
                return count[2]
            });

            drawBarChart(
                    canvas,
                    labels = labels,
                    data = cdata,
                    title = `posts report by months of the year ${y}`,
                    legendPos = 'bottom'
                    );
            postChartDiv.appendChild(canvas);
        })
    }

    if (type === 'quarter') {
        const y = document.getElementById("post-qyear").value;

        fetch(`http://localhost:8288/SocialNetworkBE/api/posts/count-posts-by-quarter/?year=${y}`, {
            method: 'GET',
        }).then(res => {
            if (!res.ok) {
                throw new Error('Không thể thống kê được!!!');
            }

            return res.json();
        }).then(counts => {
            const postChartDiv = document.getElementById("postChartDiv");
            while (postChartDiv.hasChildNodes())
                postChartDiv.removeChild(postChartDiv.childNodes[0])

            const canvas = document.createElement("canvas");

            let labels = counts.map(count => {
                return count[1]
            });
            let cdata = counts.map(count => {
                return count[2]
            });

            drawBarChart(
                    canvas,
                    labels = labels,
                    data = cdata,
                    title = `posts report by quarters of the year ${y}`,
                    legendPos = 'bottom'
                    );
            postChartDiv.appendChild(canvas);
        })
    }
}

function generateSurveyStats() {
    const sId = document.getElementById("surveyId").value;

    fetch("http://localhost:8288/SocialNetworkBE/api/posts/" + sId + "/get-questions/", {
        method: 'GET'
    }).then(res => {
        if (!res.ok) {
            throw new Error('Không thể thống kê được!!!');
        }
        return res.json();
    }).then(questions => {
        const surveyChartDiv = document.getElementById("surveyChartDiv");
        while (surveyChartDiv.hasChildNodes())
            surveyChartDiv.removeChild(surveyChartDiv.childNodes[0])
        //data: [{id: , content: }, {id: , content: }, {id: , content: }, ...]
        for (let i = 0; i < questions.length; i++) {
            fetch("http://localhost:8288/SocialNetworkBE/api/questions/" + questions[i].id + "/count-votes/", {
                method: 'GET'
            }).then(res => {
                if (!res.ok) {
                    throw new Error('Không thể thống kê được!!!');
                }
                return res.json();
            }).then(choices => {
                //data: [[id, content, count], [id, content, count], [id, content, count], ...]
                let labels = choices.map(row => {
                    return row[2]
                });
                let cdata = choices.map(row => {
                    return row[3]
                });
                const canvas = document.createElement("canvas");
                drawBarChart(
                        canvas,
                        labels = labels,
                        data = cdata,
                        title = [questions[i].content],
                        legendPos = 'bottom'
                        );
                surveyChartDiv.appendChild(canvas);
            })
        }
    })
}

function drawBarChart(ctx, labels, data, title, legendPos) {

    let colors = [];
    for (let i = 0; i < data.length; i++) {
        colors.push(`rgba(${parseInt(Math.random() * 255)}, 
            ${parseInt(Math.random() * 255)}, 
            ${parseInt(Math.random() * 255)}, 0.7)`);
    }


    return new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                    label: title,
                    data: data,
                    borderWidth: 1,
                    backgroundColor: colors
                }]
        },
        options: {
            scales: {
                x: {
                    beginAtZero: true
                },
                y: {
                    beginAtZero: true
                }

            },
            barThickness: 46,
            "plugins": {
                "legend": {
                    display: true,
                    position: legendPos
                }
            },
            maintainAspectRatio: 'false'
        },

    });
}

function drawDonutChart(ctx, labels, data, legendLabel, title, tintColors, legendPos) {

    let datasets = [{
            data: data,
            backgroundColor: tintColors,
            hoverOffset: 4
        }];

    const chart_data = {
        labels: labels,
        datasets: datasets
    };

    return new Chart(ctx, {
        type: 'doughnut',
        data: chart_data,
        options: {
            "plugins": {
                "legend": {
                    position: legendPos
                },
                title: {
                    display: true,
                    text: title
                }
            },
            maintainAspectRatio: 'false'
        }
    });
}