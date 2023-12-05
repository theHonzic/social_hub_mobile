//
//  InitialRegisterView.swift
//  iosApp
//
//  Created by Jan Janovec on 23.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct InitialRegisterView: View {
    @Binding var authPage: AuthPage
    @StateObject private var viewModel: RegisterVM = .init()
    var body: some View {
        ZStack {
            switch viewModel.state.page {
            case .identification:
                RegistrationIdentificationStep()
            case .personalDetails:
                RegistrationPersonalStep()
            case .uploadPhoto:
                RegistrationUploadPhotoStep()
            case .confirmation:
                EmptyView()
            default:
                EmptyView()
            }
            VStack {
                Spacer()
                RoundedButton(text: "Continue", foreground: Color.white, background: LinearGradient(colors: [.red, .orange], startPoint: .bottomLeading, endPoint: .topTrailing)) {}
            }
        }
        .environmentObject(viewModel)
    }
}

#Preview {
    InitialAuthView(currentAuthPage: .register)
}
