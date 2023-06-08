import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the DailyRetroJournalService.
 */

 export default class DailyRetroJournalClient extends BindingClass {

    constructor(props = {}) {
            super();

            const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getTokenOrThrow' ];

            this.bindClassMethods(methodsToBind, this);

            this.authenticator = new Authenticator();;
            this.props = props;

            axios.defaults.baseURL = process.env.API_BASE_URL;
            this.axiosClient = axios;
            this.clientLoaded();
    }

        /**
         * Run any functions that are supposed to be called once the client has loaded successfully.
         */
        clientLoaded() {
            if (this.props.hasOwnProperty("onReady")) {
                this.props.onReady(this);
            }
        }

        /**
         * Get the identity of the current user
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The user information for the current user.
         */
        async getIdentity(errorCallback) {
            try {
                const isLoggedIn = await this.authenticator.isUserLoggedIn();

                if (!isLoggedIn) {
                    return undefined; //declared but not yet assigned a value
                }

                return await this.authenticator.getCurrentUserInfo(); //if the use is logged in, return their info
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

        async login() {
            this.authenticator.login();
        }

        async logout() {
            this.authenticator.logout();
        }

        async getTokenOrThrow(unauthenticatedErrorMessage) {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();
            if (!isLoggedIn) {
                throw new Error(unauthenticatedErrorMessage);
            }

            return await this.authenticator.getUserToken();
        }

        async getAllJournalEntries(errorCallback) {
           try {
               const response = await this.axiosClient.get(`entries/all`);
               console.log(JSON.stringify(response, null, 4) + "endpoint response");
               return response.data.allJournalEntriesList; // this method comes from the GetALllJournalEntriesResult.java builder
           } catch (error) {
               this.handleError(error, errorCallback)
               return []; // Return an empty array in case of error
           }
        }

     /**
         * Helper method to log the error and run any error functions.
         * @param error The error received from the server.
         * @param errorCallback (Optional) A function to execute if the call fails.
         */
        handleError(error, errorCallback) {
            console.error(error);

            const errorFromApi = error?.response?.data?.error_message;
            if (errorFromApi) {
                console.error(errorFromApi)
                error.message = errorFromApi;
            }

            if (errorCallback) {
                errorCallback(error);
            }
        }


}