<template>
  <div class="event-shortcut container">
    <div class="row">
      <div class="col s12">
        <router-link v-if="loggedUserRole <= 2" :to="'events/edit/' + event.id" class="waves-effect green btn-floating edit-event-button">
          <i class="material-icons left events-edit">edit</i>
        </router-link>
        <router-link v-if="loggedUserRole <= 2" :to="'events/' + event.id + '/users'" class="waves-effect green btn-floating edit-event-button events-people">
          <i class="material-icons left">people</i>
        </router-link>
        <div class="card white darken-1">
          <div class="card-content black-text">
            <span class="card-title">{{event.firstName}}</span>
            <p>Where: {{event.location}}</p>
            <p>When: {{event.date}} {{event.time}}</p>
          </div>
          <div class="card-action">
            <router-link class="green-text" :to="'/events/' + event.id">More</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'EventShortcut',
  props: ['event'],
  computed: {
    ...mapGetters([
      'isLogged',
      'loggedUserRole'
    ])
  },
}
</script>

<style>
.event-shortcut .row .col {
  position: relative;
}
.edit-event-button {
  position: absolute;
  margin:  20px;
  top: 0;
  right: 0;
}
.events-people {
  right: 45px;
}

</style>
