//
//  RegisterVM.swift
//  iosApp
//
//  Created by Jan Janovec on 04.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

final class RegisterVM: RegisterViewModel, ObservableObject {
    @Published var state: RegisterContractState = .init(
        page: .identification,
        alert: .init(title: "", message: "", isPresented: false, actions: []),
        email: .init(value: "", validation: .email, triggerValidation: false),
        phoneNumber: .init(value: "", validation: .phoneNumber, triggerValidation: false),
        username: .init(value: "", validation: .username, triggerValidation: false),
        checkAccountAvailabilityState: .idle
    )
    
    override init() {
        super.init()
        collect(flow: self.uiState, collect: { state in
            self.state = state as! RegisterContractState // swiftlint:disable:this force_cast
        })
    }
}

extension RegisterVM {
    func nextStep() {
        self.setEvent(event: RegisterContractEventProceedToTheNextStep())
    }
}
