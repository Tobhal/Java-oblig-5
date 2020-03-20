<template id="moon-detail">
    <div v-if="moon" class="detail-planet-container">
        {{console.log(this.moon)}}
        <h1>{{moon.name}}</h1>
        <img v-bind:src="moon.pictureUrl" />
        <p>The mass of {{moon.name}} is {{moon.mass}} kg, it has a radius of {{moon.radius}} km,
            the eccentricity or the deviation of orbit is from a circularity is {{moon.eccentricity}}.</p>
        <p>It spins around the star {{moon.centralCelestialBody.name}} with an orbiting period of {{moon.orbitalPeriod}} days.</p>
    </div>
</template>
<script>
    Vue.component("moon-detail", {
        template: "#moon-detail",
        data: () => ({
            moon: null,
        }),
        created() {
            const planetSystemId = this.$javalin.pathParams["planet-system-id"];
            console.log("Moon system id: " + planetSystemId);
            const planetId = this.$javalin.pathParams["planet-id"];
            const moonId = this.$javalin.pathParams["moon-id"];
            console.log(moonId)
            fetch(`/api/planet-systems/${planetSystemId}/planets/${planetId}/moons/${moonId}`)

                .then(res => res.json())
                .then(res => this.moon = res)
                .catch(() => alert("Error while fetching planet"));
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

    img.planet-cover-image {
        height: 320px;
        width: 320px;
        padding-bottom: 20px;
    }
</style>