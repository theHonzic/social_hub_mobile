//
//  GlobalSettingsVM.swift
//  iosApp
//
//  Created by Jan Janovec on 12.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
final class GlobalSettingsVM: GlobalSettingsViewModel, ObservableObject {
    @Published var state = GlobalSettingsContractState(appState: GlobalSettingsContractApplicationStateLoading())
    
    override init() {
        super.init()
        collect(flow: self.uiState, collect: { state in
            self.state = state as! GlobalSettingsContractState // swiftlint:disable:this force_cast
        })
    }
}

extension GlobalSettingsVM {
    func finishOnboarding() {
        self.setEvent(event: GlobalSettingsContractEventFinishOnboarding())
    }
    
    func refreshAccount() {
        self.setEvent(event: GlobalSettingsContractEventRefreshAccount())
    }
}
