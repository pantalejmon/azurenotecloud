import React, {Component} from 'react';
import {Container, Divider, Button, Icon} from 'semantic-ui-react';
import { Redirect, Link,  } from "react-router-dom";
import ShowMoreText from 'react-show-more-text';

const ReactDOM = require('react-dom');
var globs = require('../global_vars');

import axios from 'axios'

class ViewNotes extends Component {
    constructor(props) {
        super(props);
        this.state = {user: []};
        this.notes = [];
        this.id = [];
        this.fnotes=[];
        this.GetDataRefresh = this.GetDataRefresh.bind(this);
        this.GetDataRefresh();
        console.log(globs.idToChange);

    }

    GetDataRefresh(e) {
        this.notes = [];
        axios.get('/user/current')
            .then(response => {
                for (var i = 0; i < response.data.notes.length; i++) {
                    // this.setState({user: response.data.notes[i]});
                    this.notes.push(response.data.notes[i]);
                    this.state.user.push(this.notes);
                }
                console.log('Refresh');
                this.fnotes = [];
                for (var i = 0; i < this.notes.length; i++) {
                    this.fnotes.push(
                        <div>
                            <Button.Group>
                                <Link to="/addnote">
                                <Button
                                    icon='edit'
                                    onClick={this.editOnClick.bind(this,this.notes[i].id)}
                                />
                                </Link>
                                <Button
                                    icon='delete'
                                    onClick={this.deleteOnClick.bind(this,this.notes[i].id)}
                                />

                            </Button.Group>
                            <div>Rozmiar notatki: {this.notes[i].length} </div>
                            <ShowMoreText
                                lines={2}
                                more='Show more'
                                less='Show less'
                                anchorClass=''
                                onClick={this.executeOnClick}
                            >
                                <div dangerouslySetInnerHTML={{__html: this.notes[i].content}}/>
                            </ShowMoreText>
                            <Divider/>
                        </div>
                    );
                }
                this.forceUpdate();
            })
            .catch(error => {
                console.log(error);
            });
    }



    executeOnClick(isExpanded) {
        console.log(isExpanded);
    }

    editOnClick(id) {
        console.log('edit ',id.toString());
        globs.idToChange = Number(id);
        console.log(globs.idToChange);

    };

    deleteOnClick(id) {
        const url = 'user/notes/'+id.toString();
        console.log(url);
        axios({
            method: 'delete',
            url: url,
            headers: { 'Content-Type': 'application/json' },
            })
            .then(response => {
                console.log(response);
                this.GetDataRefresh();
            })
            .catch(error => {
                console.log(error);
            });

    };


    render() {

        return (
            <div>
                <h3>
                    Your notes
                </h3>
                <div>
                    {this.fnotes}
                </div>
            </div>
        );

    }
}

export {ViewNotes};

