//
//  LoginVM.swift
//  iosApp
//
//  Created by Jan Janovec on 12.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

final class LoginVM: LoginViewModel, ObservableObject {
    @Published var state = LoginContractState(state: LoginContractILoginStateINITIAL())
}

extension LoginVM {
    func login(login: String, password: String) async {
        self.setEvent(event: LoginContractEventLogin(login: login, password: password))
    }
}
