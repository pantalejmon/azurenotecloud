import React from 'react'
import { Header, Icon, Button } from 'semantic-ui-react'

const ButtonExampleEmphasis = () => (
    <div>
        <Button primary>Primary</Button>
        <Button secondary>Secondary</Button>
    </div>
);

const HeaderExampleSettingsIcon = () => (
    <Header as='h2' icon>
        <Icon name='settings' />
        Account Settings
        <Header.Subheader>Manage your account settings and set e-mail preferences.</Header.Subheader>
    </Header>
);

export {HeaderExampleSettingsIcon, ButtonExampleEmphasis};