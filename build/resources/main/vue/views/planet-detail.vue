<template id="planet-detail">
    <div v-if="planet" class="detail-planet-container">
        {{console.log(this.planet)}}
        <h1>{{planet.name}}</h1>
        <img v-bind:src="planet.pictureUrl" />
        <p>The mass of {{planet.name}} is {{planet.mass}} kg, it has a radius of {{planet.radius}} km,
            the eccentricity or the deviation of orbit is from a circularity is {{planet.eccentricity}}.</p>
        <p>It spins around the star {{planet.centralCelestialBody.name}} with an orbiting period of {{planet.orbitalPeriod}} days.</p>
        <p v-if="moons.length != 0">{{planet.name}} also have these moons:</p>
        <div class="moons">
            <a v-for="moon in moons" v-if="planet" :href="`/planet-systems/${planetSystem.name}/planets/${planet.name}/moons/${moon.name}`">
                <p>{{moon.name}}</p>
            </a>
        </div>
    </div>
</template>
<script>
    Vue.component("planet-detail", {
        template: "#planet-detail",
        data: () => ({
            planetSystem: null,
            planet: null,
            moons: [],
            sorting: "name",
        }),
        created() {
            const planetSystemId = this.$javalin.pathParams["planet-system-id"];
            console.log("Planet system id: " + planetSystemId);
            const planetId = this.$javalin.pathParams["planet-id"];
            fetch(`/api/planet-systems/${planetSystemId}`)
                .then(res => res.json())
                .then(res => {
                    this.planetSystem = res
                })
                .catch(() => alert("Error while fetching planet system"))

            fetch(`/api/planet-systems/${planetSystemId}/planets/${planetId}`)

                .then(res => res.json())
                .then(res => this.planet = res)
                .catch(() => alert("Error while fetching planet"));

            fetch(`/api/planet-systems/${planetSystemId}/planets/${planetId}/moons`)
                .then(res => res.json())
                .then(res => this.moons = res)
                .catch(() => alert("Error while fetching planets"));
        }
    });
</script>
<style>
    ul{
       color:white;
    }
    div.detail-planet-container > p {
        max-width: 30em;
    }
    div.detail-planet-container{
        padding-top: 10px;
        overflow: hidden;
        width: 500px;
        background-color: #000000;
        color: white;
        margin: 0 auto;
        opacity: 0.96;
        text-align: center;
        -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        -moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
    }

    img {
        width: 100%;
        padding-bottom: 20px;
    }

    a{
        text-decoration: none;
        color: lightgray;
    }

    a:checked {
        color: lightgray;
    }

    .moons {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
    }

    .moons p {
        width: auto;
        margin: 0;
        padding: 5px 10px 5px 10px;
    }

    .moons p:hover {
        background-color: gray;
    }
</style>