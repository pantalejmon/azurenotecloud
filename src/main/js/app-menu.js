import React, { Component }  from 'react'
import { Input, Menu } from 'semantic-ui-react'
import { BrowserRouter as Router, Route, NavLink } from 'react-router-dom'
import { About, Home, AddNotes, ViewNotes, Service, Logout  } from './module'
var globs = require('./global_vars');


export class MenuExampleSecondary extends Component {
    state = { activeItem: 'home' };

    handleItemClick = (e, { name }) => this.setState({ activeItem: name });

    render() {
        globs.idToChange = -1;
        const { activeItem } = this.state;
        return (

            <Router>
                <div>
                <Menu secondary size='huge'>
                    <Menu.Item
                        as={NavLink} to="/index"
                        name="home"
                        active={activeItem === 'home'}
                        onClick={this.handleItemClick}
                    />

                    <Menu.Item
                        as={NavLink} to="/viewnotes"
                        name="view notes"
                        active={activeItem === 'view notes'}
                        onClick={this.handleItemClick}/>

                    <Menu.Item
                        as={NavLink} to="/addnote"
                        name="add note"
                        active={activeItem === 'add notes'}
                        onClick={this.handleItemClick}
                    />



                    <Menu.Menu position='right'>
                        <Menu.Item
                            as={NavLink} to="/logout" name='logout'
                            active={activeItem === 'logout'}
                            onClick={this.handleItemClick}
                        />
                    </Menu.Menu>
                </Menu>
                    <Route exact path="/index" component={Home} />
                    <Route exact path="/viewnotes" component={ViewNotes} />
                    <Route exact path="/addnote" component={AddNotes} />
                    <Route exact path="/logout" component={Logout} />

                </div>
            </Router>
        )
    }
}


