package com.detoksdiyetleri.injection.module

import android.arch.lifecycle.ViewModelProvider
import com.detoksdiyetleri.injection.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
@Suppress("WeakerAccess")
abstract class ViewModelModule {

    /*@Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindsLoginViewModel(loginViewModel: LoginViewModel)

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindsRegisterViewModel(registerViewModel: RegisterViewModel)


    @Binds
    @IntoMap
    @ViewModelKey(TermsAndConditionViewModel::class)
    abstract fun bindsTermsAndConditionViewModel(termsAndConditionViewModel: TermsAndConditionViewModel)


    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindsProfileViewModel(profileViewModel: ProfileViewModel)


    @Binds
    @IntoMap
    @ViewModelKey(PrivacyViewModel::class)
    abstract fun bindsPrivacyViewModel(privacyViewModel: PrivacyViewModel)

    @Binds
    @IntoMap
    @ViewModelKey(ProfileSettingsViewModel::class)
    abstract fun bindsProfileSettingsViewModel(profileSettingsViewModel: ProfileSettingsViewModel)


    @Binds
    @IntoMap
    @ViewModelKey(CreateActivityViewModel::class)
    abstract fun bindsCreateActivityViewModel(createActivityViewModel: CreateActivityViewModel)


    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    abstract fun bindsChangePasswordViewModel(changePasswordViewModel: ChangePasswordViewModel)

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel::class)
    abstract fun bindsNotificationViewModel(notificationViewModel: NotificationViewModel)



    @Binds
    @IntoMap
    @ViewModelKey(ProfileFeedViewModel::class)
    abstract fun bindsProfileFeedViewModel(profileFeedViewModel: ProfileFeedViewModel)


    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    abstract fun bindsDetailActivityViewModel(detailActivityViewModel: DetailActivityViewModel)


    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    abstract fun bindsDiscoverViewModel(discoverViewModel: DiscoverViewModel)


    @Binds
    @IntoMap
    @ViewModelKey(LocationSearchDialogViewModel::class)
    abstract fun bindsLocationSearchDialogViewModel(locationSearchDialogViewModel: LocationSearchDialogViewModel)




    @Binds
    @IntoMap
    @ViewModelKey(FollowFollowerViewModel::class)
    abstract fun bindsFollowFollowerViewModel(followFollowerViewModel: FollowFollowerViewModel)*/

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


}