  <template>
  <div class="user">
    <div class="profile">
      <div class="col s12">
        <div class="card-panel grey lighten-5 z-depth-1">

            <modal title="All changes will be discarded" @confirm="$router.go(-1)"></modal>
            <modal modalId="unsub" title="Please confirm unsubscription" @confirm="unsubscribe"></modal>
            
            <a @click.prevent="submitEditting" :class="{disabled: errors.items.length > 0}" class="waves-effect waves green btn edit-button eb-save z-depth-1">Save</a>
            <a class="waves-effect waves lighten-2 red btn edit-button eb-cancel z-depth-1 modal-trigger" href="#modal">Cancel</a>

            <div class="row edit-form">
              <form class="col s10">
                <div class="row">
                  <div class="input-field col s6">

                    <input id="first_name" type="text" v-model="currentUser.firstName" v-validate="'required|alpha'" name="First Name" :disabled="!(currentUser.firstName==loggedUser.firstName)">
                    <label class="active text-green" for="first_name"><span class="required-field">First Name</span></label>
                    <span class="helper-text red-text" >{{errors.first('First Name')}}</span>
                  </div>
                  <div class="input-field col s6">
                    <input id="last_name" type="text" v-model="currentUser.lastName" v-validate="'alpha'" name="Last Name" :disabled="!(currentUser.firstName==loggedUser.firstName)">
                    <label class="active" for="last_name"><span class="required-field">Last Name</span></label>
                    <span class="helper-text red-text" >{{errors.first('Last Name')}}</span>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s6">
                    <input id="first_phone" type="text" placeholder="+123456789" v-model="currentUser.phone1" v-validate="{ regex: /^\+([0-9]{9,12})$/ }" name="Phone number" :disabled="!(currentUser.firstName==loggedUser.firstName)">
                    <label class="active text-green" for="first_phone"><span class="required-field">Phone number</span></label>
                    <span class="helper-text red-text" >{{errors.first('Phone number') ? 'Phone number starts from `+` and has length is from 9 to 12' : ''}}</span>
                  </div>
                  <div class="input-field col s6">
                    <input id="secondary_phone" type="text" v-model="currentUser.lastPhone" v-validate="{ regex: /^\+([0-9]{9,12})$/ }" name="Phone number 2" :disabled="!(currentUser.firstName==loggedUser.firstName)">
                    <label class="active" for="secondary_phone">Phone number</label>
                    <span class="helper-text red-text" >{{errors.first('Phone number 2') ? 'Phone number starts from `+` and has length is from 9 to 12' : ''}}</span>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s12">
                    <input id="email" type="email" v-model="currentUser.email" v-validate="'required|email'" name="Email" :disabled="!(currentUser.firstName==loggedUser.firstName)">
                    <label class="active" for="email"><span class="required-field">Email</span></label>
                    <span class="helper-text red-text" >{{errors.first('Email')}}</span>
                  </div>
                </div>
                <div class="row">
                  <div v-if="isManager" class="input-field col s12">
                    <select v-model="currentUser.role" v-validate="'required'" name="Role">
                      <option value="4">Member</option>
                      <option value="3">Moderator</option>
                      <option value="2">Administrator</option>
                    </select>
                    <label><span class="required-field">Role</span></label>
                    <span class="helper-text red-text" >{{errors.first('Role')}}</span>
                  </div>
                </div>
                <div v-if="loggedUserRole <= 2" class="row">
                  <div class="input-field col s12">
                    <input id="admin-note" type="text" v-model="currentUser.annotations">  
                    <label for="admin-note">Annotations</label>
                  </div>
                </div>
              </form>
              <div class="row center red-text">{{postEdittedUserError}}</div>
            </div>
          <a v-if="currentUser.id === loggedUser.id && loggedUser.role > 1" class="waves-effect waves blue darken-2 btn edit-button eb-unsub z-depth-1 modal-trigger" href="#unsub">Unsubscribe</a>
          <a v-else-if="currentUser.id !== loggedUser.id && loggedUser.role < 4" class="waves-effect waves blue darken-2 btn edit-button eb-unsub z-depth-1" @click="block(currentUser.id)">Block User</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import Modal from '../utils/Modal.vue';

export default {
  name: 'UserEdit',
  data () {
    return {
      isManager: false
    }
  },

  methods: {
    ...mapActions([
      'putEdittedUser',
      'unsubscribeUser',
      'getUserById',
      'blockUser'
    ]),

    showButtons() {
      this.isManager = this.loggedUser.role < 3;
    },
    submitEditting() {
      this.$validator.validateAll()
        .then(() => {
          if (this.errors.items.length > 0) {
            return
          } else {
            this.putEdittedUser(this.currentUser)
          }
        })
    },
    unsubscribe() {
      this.unsubscribeUser()
    },
      block(id) {
        this.blockUser(id);
      }
  },
  computed: {
    ...mapGetters([
      'currentUser',
      'loggedUser',
      'loggedUserRole',
      'postEdittedUserError',
    ]),
  },
  components: {
    Modal
  },
  mounted() {
    this.getUserById(this.$route.params.id);
    M.Modal.init(document.querySelectorAll('.modal'));
    M.FormSelect.init(document.querySelector('select'));
    this.showButtons();
  },
  updated() {
    M.Modal.init(document.querySelectorAll('.modal'));
    M.FormSelect.init(document.querySelector('select'));
  }
}
</script>

<style>
.profile {
  height: 100%;
}
.profile .col.s12 {
  margin-top: 18px;
}
.card-panel {
  position: relative;
}
.edit-button {
  position: absolute;
  width: 100px;
}
.eb-edit, .eb-save, .eb-cancel {
  top: 10px;
  right: 10px;
}

.eb-cancel {
  top: 50px;
}
.eb-unsub {
  bottom: 10px;
  right: 10px;
  width: 130px;
}
.eb-password {
  top: 50px;
  right: 10px;
  width: auto;
}
.edit-form {
  width: 100%;
}
.required-field::after {
  content: " *";
  color: red;
  font-size: 0.8em;
}

</style>
