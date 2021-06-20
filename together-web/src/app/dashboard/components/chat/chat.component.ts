import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Message } from 'stompjs';

import { ConfigService, STOMPService } from '../../../service/index';
import { User } from '../../../model/user';
import { ChatMessage } from '../../../model/chat-message';

@Component({
    selector: 'app-chat',
    templateUrl: './chat.component.html',
    styleUrls: ['./chat.component.scss'],
    providers: [STOMPService, ConfigService]
})
export class ChatComponent implements OnInit, OnDestroy {
    user: User;
    model: any = {};

    // Stream of messages
    public messages: Observable<Message>;

    // Array of historic message (bodies)
    public mq: Array<ChatMessage> = [];

    constructor(private _stompService: STOMPService,
        private _configService: ConfigService) { }

    ngOnInit() {
        this._configService.getConfig('src/api/config.json').then(
            config => {
                // ... then pass it to (and connect) STOMP:
                this._stompService.configure(config);
                this._stompService.try_connect().then(this.on_connect);
            });

        if (typeof (Storage) !== 'undefined') {
            this.user = JSON.parse(localStorage.getItem('currentUser'));
        } else {
            console.log('Sorry! No Web Storage support..');
        }
    }

    ngOnDestroy() {
        this._stompService.disconnect();
    }

    /** Callback on_connect to queue */
    public on_connect = () => {

        // Store local reference to Observable
        // for use with template ( | async )
        this.messages = this._stompService.messages;

        // Subscribe a function to be run on_next message
        this.messages.subscribe(this.on_next);
    }

    /** Consume a message from the _stompService */
    public on_next = (message: Message) => {
        let chatMessage: ChatMessage = JSON.parse(message.body);
        this.mq.push(chatMessage);
        // console.log(chatMessage.from,chatMessage.message);
    }

    sendMessage() {
        if (this.model.message != null && typeof (this.model.message) !== 'undefined' && this.model.message.trim() !== '') {
            this._stompService.publish(`{ "from": "${this.user.email}", "message": "${this.model.message}" }`);
            this.model.message = '';
        }
    }
}
